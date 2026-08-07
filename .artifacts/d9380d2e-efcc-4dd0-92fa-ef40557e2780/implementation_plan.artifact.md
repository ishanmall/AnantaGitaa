# Fix Kotlin Compile Daemon Termination

The Kotlin compile daemon is failing with "terminated unexpectedly" (exit code 0). This usually happens due to memory constraints, JVM argument conflicts, or corrupted daemon states. This plan increases the allocated memory and stabilizes the daemon configuration.

## Proposed Changes

### Build Configuration

#### [MODIFY] [gradle.properties](file:///C:/Users/ishan/AndroidStudioProjects/AnantaGita/gradle.properties)
- Increase Gradle JVM heap size to 4096m.
- Explicitly set Kotlin daemon JVM arguments.
- Disable Kotlin daemon performance threshold to avoid unnecessary restarts.

#### [MODIFY] [libs.versions.toml](file:///C:/Users/ishan/AndroidStudioProjects/AnantaGita/gradle/libs.versions.toml)
- Update AGP version to `8.12.3` to match `settings.gradle.kts`.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/ishan/AndroidStudioProjects/AnantaGita/build.gradle.kts)
- Ensure consistent AGP version in buildscript (if used).

## Verification Plan

### Automated Tests
- Run `./gradlew :app:compileDebugKotlin` to ensure the Kotlin daemon starts correctly and completes the compilation.
- Run `./gradlew help` to verify basic Gradle connectivity.

### Manual Verification
- The user should run `./gradlew --stop` before the next build to ensure fresh daemons are started with the new configuration.
