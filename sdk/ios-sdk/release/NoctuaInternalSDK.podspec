Pod::Spec.new do |spec|
    spec.name                     = 'NoctuaInternalSDK'
    spec.version                  = '0.11.0'
    spec.homepage                 = 'https://github.com/NoctuaLabs/noctua-internal-native-sdk'
    spec.source                   = { :git => 'git@github.com:NoctuaLabs/noctua-internal-native-sdk.git', :tag => 'ios-sdk-v0.11.0' }
    spec.authors                  = 'Noctua Labs'
    spec.license                  = { :type => 'MIT', :file => 'LICENSE' }
    spec.summary                  = 'Noctua internal iOS SDK'
    spec.vendored_frameworks      = 'NoctuaInternalSDK.xcframework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target    = '14.0'
                
                
                
                
                
                
end