## [android-sdk-v0.12.0] - 2025-12-18

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
- Add noctua internal sdk xcframework

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

### 💼 Other

- Bump sdk version to 0.11.0

### 🚜 Refactor

- Change module library into sdk
- Rename gg.noctua.analytics to gg.noctua.internal
- Project id
- Namespace to com.noctuagames.labs.sdk.utils

### ⚙️ Miscellaneous Tasks

- Bump SDK versions to 0.5.0 ->0.6.0
