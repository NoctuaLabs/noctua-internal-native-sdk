# CLAUDE.md

## Project

Noctua Internal SDK — Kotlin Multiplatform analytics SDK (Android, iOS, Desktop). Offline-first event tracking with Room persistence and batch API delivery.

## Commands

```bash
# Build
./gradlew build                    # Full build (all platforms)
./gradlew :sdk:assembleRelease     # Android AAR
./gradlew podPublishReleaseXCFramework  # iOS XCFramework
./gradlew compileKotlinDesktop     # Desktop JVM

# Test
./gradlew allTests                 # All platforms
./gradlew testDebugUnitTest        # Android unit tests
./gradlew iosSimulatorArm64Test    # iOS simulator tests
./gradlew desktopTest              # Desktop tests
./gradlew check                    # Tests + lint

# iOS Example App (from iosApp/)
cd iosApp && xcodegen generate     # Generate Xcode project
xcodebuild -project NoctuaSDKExample.xcodeproj -scheme iosApp -destination "id=<DEVICE_UDID>" DEVELOPMENT_TEAM="2TFDF8BB6J" build

# Lint
./gradlew lint                     # Android lint
./gradlew lintFix                  # Auto-fix lint issues
```

## Architecture

```
sdk/src/
├── commonMain/kotlin/             # Shared business logic
│   ├── NoctuaInternal.kt          # Public API (singleton object)
│   ├── presenter/                 # NoctuaInternalPresenter (orchestrator)
│   ├── data/local/                # Room DB, DAOs, entities (see package note in Gotchas)
│   │   ├── NoctuaDatabase.kt      # @Database v2; tables: events + external_events
│   │   ├── DatabaseFactory.kt     # expect/actual DB builder
│   │   ├── dao/EventDao.kt        # internal session/heartbeat events (data.database.dao pkg)
│   │   ├── dao/ExternalEventDao.kt# SDK-consumer external events (data.local.dao pkg)
│   │   ├── entity/EventEntity.kt  # table: events (id, events JSON blob)
│   │   └── entity/ExternalEventEntity.kt # table: external_events (id, event_json, created_at)
│   ├── data/remote/               # Ktor HTTP client, NetworkStatusProvider
│   ├── data/models/               # NoctuaConfig (parsed from noctuagg.json)
│   ├── di/                        # Koin DI setup (sharedModule + expect platformModule)
│   └── utils/                     # SessionTracker, ExperimentManager, DeviceUtils, AppLogger
├── androidMain/kotlin/            # Android: OkHttp engine, AppContext, InternalNoctuaApp
├── iosMain/kotlin/                # iOS: Darwin engine, IOSLifecycleObserver
├── desktopMain/kotlin/            # Desktop: JVM implementations
└── commonTest/kotlin/             # Shared tests (unit + integration)
```

## Key Patterns

- **Expect/Actual**: `platformModule` (DI), `loadAppConfig()`, `disposePlatformLifecycle()`, `DatabaseFactory`, `DeviceUtils`, `Utils`, `isNetworkAvailable()`, `AppContext` all have platform-specific implementations
- **DI**: Koin v4 — `initKoin()` starts shared + platform modules. Config loaded at DI startup as single instance. Android also exposes `initKoinManually(context: Context)` for manual init.
- **Public API**: `NoctuaInternal` object delegates to `NoctuaInternalPresenter` via Koin. Must init Koin first.
  - External event store (per-row): `insertExternalEvent`, `getExternalEventsBatch(limit, offset, cb)`, `deleteExternalEventsByIds(idsJson, cb)`, `getExternalEventCount(cb)`
  - External event store (legacy bulk): `saveExternalEvents(jsonString)`, `getExternalEvents(cb)`, `deleteExternalEvents()`
  - Session: `onInternalNoctuaApplicationPause(pause)`, `onInternalNoctuaDispose()`, `setSessionTag/getSessionTag`, `setSessionExtraParams(params)`
  - Experiments: `setExperiment/getExperiment`, `setGeneralExperiment(key, value)/getGeneralExperiment(key)` (one-arg `setGeneralExperiment(experiment)` is deprecated — it stored the name as both key and value)
  - Tracking: `trackCustomEvent(name, props)`, `trackCustomEventWithRevenue(name, revenue, currency, props)`
- **Database**: Room 2.7.2 with bundled SQLite. DB version 2 (auto-migration 1→2 renames `events.events` → `events.event_json`). KSP processors per platform (`kspAndroid`, `kspIosArm64`, etc.). Schemas in `sdk/schemas/`
- **HTTP**: Ktor 3.1.2 — OkHttp engine (Android/Desktop), Darwin engine (iOS). Events sent as NDJSON batches to `https://sdk-tracker.noctuaprojects.com/api/v1`. Logging is sandbox-only (`sandboxEnabled=true`), capped at `HEADERS` level with `X-CLIENT-ID`/`X-DEVICE-ID` redacted — never `ALL`/`BODY` (avoids OOM on large batches).
- **Network**: `NetworkStatusProvider` — cached `isConnected()` check (10 s TTL, platform `isNetworkAvailable()` underneath)
- **Session tracking**: `SessionTracker` with `SessionTrackerConfig` (heartbeat every 60 s, session timeout 5 min). Auto-starts on init; emits `session_pause` event on background.
- **User engagement**: When `noctua.nativeInternalTrackerEnabled` is true, a `noctua_user_engagement` event is emitted **before** each session event (Unity SDK parity). Payload: `engagement_time_msec` (incremental foreground time since last send — 0 on start/resume, accrues only in foreground) + `lifecycle` ∈ {start, foreground, pause, end}. It also receives the `feature_tag`/extra-params treatment like other session events.
- **Config**: `noctuagg.json` loaded from app bundle/assets. `sandboxEnabled` flag gates verbose logging. Key config fields: `clientId`, `gameId`, `noctua.offlineFirstEnabled`, `noctua.iaaEnabled`, `noctua.sentryDsnUrl`.
- **Android entry point**: `InternalNoctuaApp : Application` — registers `AppContext` and calls `initKoin`. Must be declared in AndroidManifest.
- **iOS lifecycle**: `IOSLifecycleObserver` mirrors Android pause/resume to `SessionTracker`.
- **BuildConfig**: `BuildConfig.SDK_VERSION` auto-generated at build time from `gradle.properties` — never edit manually.

## Versioning

- **Single source of truth**: `gradle.properties` → `sdk.version=0.17.0`
- **Bump**: CI uses `git-cliff --bumped-version` (semantic versioning) — `feat:` bumps minor (0.17.0 → 0.18.0), `fix:`/other bumps patch (0.17.0 → 0.17.1)
- **Tags**: `android-sdk-v0.17.0` / `ios-sdk-v0.17.0`
- **Publish**: Android → Maven Central, iOS → CocoaPods private repo (`NoctuaLabs/Specs`)

## Gotchas

- iOS builds require macOS runner with Xcode; all iOS CI jobs are manual triggers
- Room KSP must be added per-platform target (not just once) — see `sdk/build.gradle.kts`
- `kotlin.native.ignoreDisabledTargets=true` — builds succeed even if native target toolchain missing
- iOS Info.plist requires `CADisableMinimumFrameDurationOnPhone=true` for Compose Multiplatform
- CI uses `git pull --rebase` before push to avoid non-fast-forward rejections
- XCFramework artifacts are committed to git (`sdk/ios-sdk/release/`) before release tag
- Version bump commits use `[skip ci]` to avoid re-triggering pipeline
- Changelog generated by git-cliff, filtered by tag pattern and path (`sdk/**`)
- **Package/path mismatch**: Core DB classes (`NoctuaDatabase`, `DatabaseFactory`, `EventDao`, `EventEntity`) live under the `data/local/` file path but use the `com.noctuagames.labs.sdk.data.database` package. External event classes (`ExternalEventDao`, `ExternalEventEntity`) use the `data.local` package. This split is intentional — don't "fix" it without a full migration.
- **Two event tables**: `events` (internal session/heartbeat events flushed to server) vs `external_events` (SDK-consumer store, not auto-flushed). They share the same `NoctuaDatabase` instance.
- **Flush semantics**: `events` are uploaded in batches of 100 and deleted **by ID only after a confirmed send** (never `deleteAll()` — events inserted mid-upload must survive). Store is capped at 2000 rows, oldest evicted first. The presenter is a Koin `single` (it owns the scope/SessionTracker); `NoctuaInternal` resolves it per call and no-ops with an error log if Koin isn't started.
- **Presenter tests live in `desktopTest`** (not commonTest): constructing the presenter touches `DeviceUtils`, which needs a real Context on Android unit tests. Desktop actuals are headless-safe.

## Git Workflow

- **Main branch**: `main`
- **Commit style**: Conventional commits — `fix(ci):`, `feat:`, `refactor:`, `build:`
- **Remotes**: GitLab (primary), GitHub (mirror). CI pushes releases to both
- **CI**: GitLab CI — test → bump-version → build → deploy → release → publish
