Pod::Spec.new do |spec|
    spec.name                     = 'NoctuaInternalSDK'
    spec.version                  = '0.13.0'
    spec.homepage                 = 'https://github.com/NoctuaLabs/noctua-internal-native-sdk'
    spec.source                   = { :http=> ''}
    spec.authors                  = 'Noctua Labs'
    spec.license                  = { :type => 'MIT', :file => 'LICENSE' }
    spec.summary                  = 'Noctua internal iOS SDK'
    spec.vendored_frameworks      = 'build/cocoapods/framework/NoctuaInternalSDK.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target    = '14.0'
                
                
    if !Dir.exist?('build/cocoapods/framework/NoctuaInternalSDK.framework') || Dir.empty?('build/cocoapods/framework/NoctuaInternalSDK.framework')
        raise "

        Kotlin framework 'NoctuaInternalSDK' doesn't exist yet, so a proper Xcode project can't be generated.
        'pod install' should be executed after running ':generateDummyFramework' Gradle task:

            ./gradlew :sdk:generateDummyFramework

        Alternatively, proper pod installation is performed during Gradle sync in the IDE (if Podfile location is set)"
    end
                
    spec.xcconfig = {
        'ENABLE_USER_SCRIPT_SANDBOXING' => 'NO',
    }
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':sdk',
        'PRODUCT_MODULE_NAME' => 'NoctuaInternalSDK',
    }
                
    spec.script_phases = [
        {
            :name => 'Build NoctuaInternalSDK',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end