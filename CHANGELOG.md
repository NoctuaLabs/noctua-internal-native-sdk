# Changelog

All notable changes to this project will be documented in this file.
Versions follow [Semantic Versioning](https://semver.org/).

## [android-sdk-v0.19.0] - 2026-06-15

### misc

- Bump sdk version to 0.11.0
- Bump version manually by @MozeeB

### ⚙️ Miscellaneous

- Bump SDK versions to 0.5.0 ->0.6.0
- Bump version to 0.15.0

### ✨ Improvements

- Change module library into sdk
- Rename gg.noctua.analytics to gg.noctua.internal
- Project id
- Namespace to com.noctuagames.labs.sdk.utils
- Move version to gradle.properties, add sandbox-aware logging, fix iOS CI

### 🐛 Bug Fixes

- Received status code 403 from server: Forbidden
- Update device utils
- Change version to use from constant class
- Update gradle version and refactor bundle id
- Gitlab ci
- Maven central
- Kotlin version, compilesdk and agp is too higher
- Update version sdk
- Noctuagg.json not loaded properly
- Koin di not working properly - ios by @MozeeB
- Vendored framworks path by @MozeeB
- Ignore unknown keys by @MozeeB
- Replace manual SQLiteConnection migration with Room @AutoMigration
- Add defaultValue for created_at column in AutoMigration
- *(ios)* Set vendored_frameworks to repo-root-relative path by @MozeeB

### 🚀 Features

- Setup CI/CD project
- Add get platform stype
- Add track session heartbeat
- Add offline-first behavior
- Add session tags for TSPU tracking
- Add and configure Room and Koin in the project
- Add function init koin manually
- Add session id for all events
- Reduce server load by batching and sending 101 events per request
- Add function set session extra params
- Add clear session extra params after save data locally
- Expose Room-based event persistence (save/get/delete)
- Setup cocoapods
- Add noctua internal sdk xcframework by @MozeeB
- Add per-row event storage for unlimited event tracking
- Fix iOS heartbeat, add tests, example app, and CI pipeline fixes
- Add iOS example app, UI tests, and CLAUDE.md by @MozeeB
- Add user engagement tracking and harden presenter reliability by @MozeeB
- Gate native tracker entirely on nativeInternalTrackerEnabled
- Runtime sandboxEnabled override (native-sdk parity)


