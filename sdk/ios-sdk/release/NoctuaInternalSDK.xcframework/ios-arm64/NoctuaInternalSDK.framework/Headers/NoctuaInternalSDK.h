#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class NISDKAdjustPlatformConfig, NISDKAdjustPlatformConfigCompanion, NISDKAppContext, NISDKAppLogger, NISDKConstants, NISDKDataErrorLocal, NISDKDataErrorRemote, NISDKDeviceUtils, NISDKEventDao_ImplCompanion, NISDKEventEntity, NISDKExternalEventDao_ImplCompanion, NISDKExternalEventEntity, NISDKFacebookPlatformConfig, NISDKFacebookPlatformConfigCompanion, NISDKFirebasePlatformConfig, NISDKFirebasePlatformConfigCompanion, NISDKKoin_coreBeanDefinition<T>, NISDKKoin_coreCallbacks<T>, NISDKKoin_coreExtensionManager, NISDKKoin_coreInstanceFactory<T>, NISDKKoin_coreInstanceFactoryCompanion, NISDKKoin_coreInstanceRegistry, NISDKKoin_coreKind, NISDKKoin_coreKoin, NISDKKoin_coreKoinApplication, NISDKKoin_coreKoinApplicationCompanion, NISDKKoin_coreKoinDefinition<R>, NISDKKoin_coreLevel, NISDKKoin_coreLockable, NISDKKoin_coreLogger, NISDKKoin_coreModule, NISDKKoin_coreParametersHolder, NISDKKoin_corePropertyRegistry, NISDKKoin_coreResolutionContext, NISDKKoin_coreScope, NISDKKoin_coreScopeDSL, NISDKKoin_coreScopeRegistry, NISDKKoin_coreScopeRegistryCompanion, NISDKKoin_coreSingleInstanceFactory<T>, NISDKKotlinArray<T>, NISDKKotlinByteArray, NISDKKotlinByteIterator, NISDKKotlinEnum<E>, NISDKKotlinEnumCompanion, NISDKKotlinException, NISDKKotlinIllegalStateException, NISDKKotlinIntArray, NISDKKotlinIntIterator, NISDKKotlinLazyThreadSafetyMode, NISDKKotlinNothing, NISDKKotlinRuntimeException, NISDKKotlinThrowable, NISDKKotlinx_serialization_coreSerialKind, NISDKKotlinx_serialization_coreSerializersModule, NISDKKotlinx_serialization_jsonClassDiscriminatorMode, NISDKKotlinx_serialization_jsonJson, NISDKKotlinx_serialization_jsonJsonConfiguration, NISDKKotlinx_serialization_jsonJsonDefault, NISDKKotlinx_serialization_jsonJsonElement, NISDKKotlinx_serialization_jsonJsonElementCompanion, NISDKNetworkStatusProvider, NISDKNoctuaConfig, NISDKNoctuaConfigCompanion, NISDKNoctuaDatabase, NISDKNoctuaDatabaseCompanion, NISDKNoctuaDatabaseConstructor, NISDKNoctuaFeatureConfig, NISDKNoctuaFeatureConfigCompanion, NISDKNoctuaInternal, NISDKPlatformAdjustConfig, NISDKPlatformAdjustConfigCompanion, NISDKPlatformFacebookConfig, NISDKPlatformFacebookConfigCompanion, NISDKPlatformFirebaseConfig, NISDKPlatformFirebaseConfigCompanion, NISDKPlatformType, NISDKRemoteFeatureFlags, NISDKRemoteFeatureFlagsCompanion, NISDKResultError<__covariant E>, NISDKResultSuccess<__covariant D>, NISDKRoom_runtimeInvalidationTracker, NISDKRoom_runtimeMigration, NISDKRoom_runtimeRoomDatabase, NISDKRoom_runtimeRoomDatabaseBuilder<T>, NISDKRoom_runtimeRoomDatabaseCallback, NISDKRoom_runtimeRoomDatabaseJournalMode, NISDKRoom_runtimeRoomOpenDelegate, NISDKRoom_runtimeRoomOpenDelegateValidationResult, NISDKSessionTrackerConfig, NISDKStringListTypeConverter;

@protocol NISDKDataError, NISDKError, NISDKEventDao, NISDKExternalEventDao, NISDKKoin_coreKoinComponent, NISDKKoin_coreKoinExtension, NISDKKoin_coreKoinScopeComponent, NISDKKoin_coreQualifier, NISDKKoin_coreScopeCallback, NISDKKotlinAnnotation, NISDKKotlinAutoCloseable, NISDKKotlinComparable, NISDKKotlinCoroutineContext, NISDKKotlinCoroutineContextElement, NISDKKotlinCoroutineContextKey, NISDKKotlinIterator, NISDKKotlinKAnnotatedElement, NISDKKotlinKClass, NISDKKotlinKClassifier, NISDKKotlinKDeclarationContainer, NISDKKotlinLazy, NISDKKotlinx_coroutines_coreCoroutineScope, NISDKKotlinx_coroutines_coreFlow, NISDKKotlinx_coroutines_coreFlowCollector, NISDKKotlinx_serialization_coreCompositeDecoder, NISDKKotlinx_serialization_coreCompositeEncoder, NISDKKotlinx_serialization_coreDecoder, NISDKKotlinx_serialization_coreDeserializationStrategy, NISDKKotlinx_serialization_coreEncoder, NISDKKotlinx_serialization_coreKSerializer, NISDKKotlinx_serialization_coreSerialDescriptor, NISDKKotlinx_serialization_coreSerialFormat, NISDKKotlinx_serialization_coreSerializationStrategy, NISDKKotlinx_serialization_coreSerializersModuleCollector, NISDKKotlinx_serialization_coreStringFormat, NISDKKotlinx_serialization_jsonJsonNamingStrategy, NISDKResult, NISDKRoom_runtimeAutoMigrationSpec, NISDKRoom_runtimeRoomDatabaseConstructor, NISDKRoom_runtimeRoomOpenDelegateMarker, NISDKSqliteSQLiteConnection, NISDKSqliteSQLiteDriver, NISDKSqliteSQLiteStatement;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface NISDKBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface NISDKBase (NISDKBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface NISDKMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface NISDKMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorNISDKKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface NISDKNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface NISDKByte : NISDKNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface NISDKUByte : NISDKNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface NISDKShort : NISDKNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface NISDKUShort : NISDKNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface NISDKInt : NISDKNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface NISDKUInt : NISDKNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface NISDKLong : NISDKNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface NISDKULong : NISDKNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface NISDKFloat : NISDKNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface NISDKDouble : NISDKNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface NISDKBoolean : NISDKNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NoctuaInternal")))
@interface NISDKNoctuaInternal : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)noctuaInternal __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKNoctuaInternal *shared __attribute__((swift_name("shared")));
- (void)deleteExternalEvents __attribute__((swift_name("deleteExternalEvents()")));
- (NSString *)getExperiment __attribute__((swift_name("getExperiment()")));
- (void)getExternalEventsOnResult:(void (^)(NSArray<NSString *> *))onResult __attribute__((swift_name("getExternalEvents(onResult:)")));
- (NSString *)getGeneralExperimentExperimentKey:(NSString *)experimentKey __attribute__((swift_name("getGeneralExperiment(experimentKey:)")));
- (NSString *)getSessionTag __attribute__((swift_name("getSessionTag()")));
- (void)onInternalNoctuaApplicationPausePauseStatus:(BOOL)pauseStatus __attribute__((swift_name("onInternalNoctuaApplicationPause(pauseStatus:)")));
- (void)onInternalNoctuaDispose __attribute__((swift_name("onInternalNoctuaDispose()")));
- (void)saveExternalEventsJsonString:(NSString *)jsonString __attribute__((swift_name("saveExternalEvents(jsonString:)")));
- (void)setExperimentExperiment:(NSString *)experiment __attribute__((swift_name("setExperiment(experiment:)")));
- (void)setGeneralExperimentExperiment:(NSString *)experiment __attribute__((swift_name("setGeneralExperiment(experiment:)")));
- (void)setSessionExtraParamsParams:(NSDictionary<NSString *, id> *)params __attribute__((swift_name("setSessionExtraParams(params:)")));
- (void)setSessionTagTag:(NSString *)tag __attribute__((swift_name("setSessionTag(tag:)")));
- (void)trackCustomEventEventName:(NSString *)eventName properties:(NSDictionary<NSString *, id> *)properties __attribute__((swift_name("trackCustomEvent(eventName:properties:)")));
- (void)trackCustomEventWithRevenueEventName:(NSString *)eventName revenue:(double)revenue currency:(NSString *)currency properties:(NSDictionary<NSString *, id> *)properties __attribute__((swift_name("trackCustomEventWithRevenue(eventName:revenue:currency:properties:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DatabaseFactory")))
@interface NISDKDatabaseFactory : NISDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<NISDKNoctuaDatabase *> *)create __attribute__((swift_name("create()")));
@end

__attribute__((swift_name("Room_runtimeRoomDatabase")))
@interface NISDKRoom_runtimeRoomDatabase : NISDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)close __attribute__((swift_name("close()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
*/
- (NSArray<NISDKRoom_runtimeMigration *> *)createAutoMigrationsAutoMigrationSpecs:(NSDictionary<id<NISDKKotlinKClass>, id<NISDKRoom_runtimeAutoMigrationSpec>> *)autoMigrationSpecs __attribute__((swift_name("createAutoMigrations(autoMigrationSpecs:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NISDKRoom_runtimeInvalidationTracker *)createInvalidationTracker __attribute__((swift_name("createInvalidationTracker()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (id<NISDKRoom_runtimeRoomOpenDelegateMarker>)createOpenDelegate __attribute__((swift_name("createOpenDelegate()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
*/
- (id<NISDKKotlinx_coroutines_coreCoroutineScope>)getCoroutineScope __attribute__((swift_name("getCoroutineScope()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
*/
- (NSSet<id<NISDKKotlinKClass>> *)getRequiredAutoMigrationSpecClasses __attribute__((swift_name("getRequiredAutoMigrationSpecClasses()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NSDictionary<id<NISDKKotlinKClass>, NSArray<id<NISDKKotlinKClass>> *> *)getRequiredTypeConverterClasses __attribute__((swift_name("getRequiredTypeConverterClasses()")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
*/
- (id)getTypeConverterKlass:(id<NISDKKotlinKClass>)klass __attribute__((swift_name("getTypeConverter(klass:)")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)internalInitInvalidationTrackerConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("internalInitInvalidationTracker(connection:)")));
@property (readonly) NISDKRoom_runtimeInvalidationTracker *invalidationTracker __attribute__((swift_name("invalidationTracker")));
@end

__attribute__((swift_name("NoctuaDatabase")))
@interface NISDKNoctuaDatabase : NISDKRoom_runtimeRoomDatabase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) NISDKNoctuaDatabaseCompanion *companion __attribute__((swift_name("companion")));
@property (readonly) id<NISDKEventDao> eventDao __attribute__((swift_name("eventDao")));
@property (readonly) id<NISDKExternalEventDao> externalEventDao __attribute__((swift_name("externalEventDao")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NoctuaDatabase.Companion")))
@interface NISDKNoctuaDatabaseCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKNoctuaDatabaseCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *DB_NAME __attribute__((swift_name("DB_NAME")));
@end

__attribute__((swift_name("Room_runtimeRoomDatabaseConstructor")))
@protocol NISDKRoom_runtimeRoomDatabaseConstructor
@required
- (NISDKRoom_runtimeRoomDatabase *)initialize __attribute__((swift_name("initialize()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NoctuaDatabaseConstructor")))
@interface NISDKNoctuaDatabaseConstructor : NISDKBase <NISDKRoom_runtimeRoomDatabaseConstructor>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)noctuaDatabaseConstructor __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKNoctuaDatabaseConstructor *shared __attribute__((swift_name("shared")));
- (NISDKNoctuaDatabase *)initialize __attribute__((swift_name("initialize()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NoctuaDatabase_Impl")))
@interface NISDKNoctuaDatabase_Impl : NISDKNoctuaDatabase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSArray<NISDKRoom_runtimeMigration *> *)createAutoMigrationsAutoMigrationSpecs:(NSDictionary<id<NISDKKotlinKClass>, id<NISDKRoom_runtimeAutoMigrationSpec>> *)autoMigrationSpecs __attribute__((swift_name("createAutoMigrations(autoMigrationSpecs:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NISDKRoom_runtimeInvalidationTracker *)createInvalidationTracker __attribute__((swift_name("createInvalidationTracker()")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NISDKRoom_runtimeRoomOpenDelegate *)createOpenDelegate __attribute__((swift_name("createOpenDelegate()")));
- (NSSet<id<NISDKKotlinKClass>> *)getRequiredAutoMigrationSpecClasses __attribute__((swift_name("getRequiredAutoMigrationSpecClasses()")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NSDictionary<id<NISDKKotlinKClass>, NSArray<id<NISDKKotlinKClass>> *> *)getRequiredTypeConverterClasses __attribute__((swift_name("getRequiredTypeConverterClasses()")));
@property (readonly) id<NISDKEventDao> eventDao __attribute__((swift_name("eventDao")));
@property (readonly) id<NISDKExternalEventDao> externalEventDao __attribute__((swift_name("externalEventDao")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StringListTypeConverter")))
@interface NISDKStringListTypeConverter : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)stringListTypeConverter __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKStringListTypeConverter *shared __attribute__((swift_name("shared")));
- (NSString *)fromListList:(NSArray<NSString *> *)list __attribute__((swift_name("fromList(list:)")));
- (NSArray<NSString *> *)fromStringValue:(NSString *)value __attribute__((swift_name("fromString(value:)")));
- (NSString * _Nullable)mapToStringMap:(NSDictionary<NSString *, id> * _Nullable)map __attribute__((swift_name("mapToString(map:)")));
- (NSDictionary<NSString *, id> * _Nullable)stringToMapString:(NSString * _Nullable)string __attribute__((swift_name("stringToMap(string:)")));
@end

__attribute__((swift_name("EventDao")))
@protocol NISDKEventDao
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteAllWithCompletionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteAll(completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getAllWithCompletionHandler:(void (^)(NSArray<NISDKEventEntity *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getAll(completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertEvent:(NISDKEventEntity *)event completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insert(event:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EventDao_Impl")))
@interface NISDKEventDao_Impl : NISDKBase <NISDKEventDao>
- (instancetype)initWith__db:(NISDKRoom_runtimeRoomDatabase *)__db __attribute__((swift_name("init(__db:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKEventDao_ImplCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteAllWithCompletionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteAll(completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getAllWithCompletionHandler:(void (^)(NSArray<NISDKEventEntity *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getAll(completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertEvent:(NISDKEventEntity *)event completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insert(event:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EventDao_Impl.Companion")))
@interface NISDKEventDao_ImplCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKEventDao_ImplCompanion *shared __attribute__((swift_name("shared")));
- (NSArray<id<NISDKKotlinKClass>> *)getRequiredConverters __attribute__((swift_name("getRequiredConverters()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EventEntity")))
@interface NISDKEventEntity : NISDKBase
- (instancetype)initWithId:(int64_t)id events:(NSString *)events __attribute__((swift_name("init(id:events:)"))) __attribute__((objc_designated_initializer));
- (NISDKEventEntity *)doCopyId:(int64_t)id events:(NSString *)events __attribute__((swift_name("doCopy(id:events:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *events __attribute__((swift_name("events")));
@property (readonly) int64_t id __attribute__((swift_name("id")));
@end

__attribute__((swift_name("ExternalEventDao")))
@protocol NISDKExternalEventDao
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteAllWithCompletionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteAll(completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getAllWithCompletionHandler:(void (^)(NSArray<NISDKExternalEventEntity *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getAll(completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertEvent:(NISDKExternalEventEntity *)event completionHandler_:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insert(event:completionHandler_:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExternalEventDao_Impl")))
@interface NISDKExternalEventDao_Impl : NISDKBase <NISDKExternalEventDao>
- (instancetype)initWith__db:(NISDKRoom_runtimeRoomDatabase *)__db __attribute__((swift_name("init(__db:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKExternalEventDao_ImplCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteAllWithCompletionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("deleteAll(completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getAllWithCompletionHandler:(void (^)(NSArray<NISDKExternalEventEntity *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getAll(completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)insertEvent:(NISDKExternalEventEntity *)event completionHandler_:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("insert(event:completionHandler_:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExternalEventDao_Impl.Companion")))
@interface NISDKExternalEventDao_ImplCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKExternalEventDao_ImplCompanion *shared __attribute__((swift_name("shared")));
- (NSArray<id<NISDKKotlinKClass>> *)getRequiredConverters __attribute__((swift_name("getRequiredConverters()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExternalEventEntity")))
@interface NISDKExternalEventEntity : NISDKBase
- (instancetype)initWithId:(int64_t)id events:(NSString *)events __attribute__((swift_name("init(id:events:)"))) __attribute__((objc_designated_initializer));
- (NISDKExternalEventEntity *)doCopyId:(int64_t)id events:(NSString *)events __attribute__((swift_name("doCopy(id:events:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *events __attribute__((swift_name("events")));
@property (readonly) int64_t id __attribute__((swift_name("id")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AdjustPlatformConfig")))
@interface NISDKAdjustPlatformConfig : NISDKBase
- (instancetype)initWithAppToken:(NSString * _Nullable)appToken eventMap:(NSDictionary<NSString *, NSString *> *)eventMap __attribute__((swift_name("init(appToken:eventMap:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKAdjustPlatformConfigCompanion *companion __attribute__((swift_name("companion")));
- (NISDKAdjustPlatformConfig *)doCopyAppToken:(NSString * _Nullable)appToken eventMap:(NSDictionary<NSString *, NSString *> *)eventMap __attribute__((swift_name("doCopy(appToken:eventMap:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable appToken __attribute__((swift_name("appToken")));
@property (readonly) NSDictionary<NSString *, NSString *> *eventMap __attribute__((swift_name("eventMap")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AdjustPlatformConfig.Companion")))
@interface NISDKAdjustPlatformConfigCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKAdjustPlatformConfigCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FacebookPlatformConfig")))
@interface NISDKFacebookPlatformConfig : NISDKBase
- (instancetype)initWithAppId:(NSString * _Nullable)appId clientToken:(NSString * _Nullable)clientToken displayName:(NSString * _Nullable)displayName enableDebug:(NISDKBoolean * _Nullable)enableDebug __attribute__((swift_name("init(appId:clientToken:displayName:enableDebug:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKFacebookPlatformConfigCompanion *companion __attribute__((swift_name("companion")));
- (NISDKFacebookPlatformConfig *)doCopyAppId:(NSString * _Nullable)appId clientToken:(NSString * _Nullable)clientToken displayName:(NSString * _Nullable)displayName enableDebug:(NISDKBoolean * _Nullable)enableDebug __attribute__((swift_name("doCopy(appId:clientToken:displayName:enableDebug:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable appId __attribute__((swift_name("appId")));
@property (readonly) NSString * _Nullable clientToken __attribute__((swift_name("clientToken")));
@property (readonly) NSString * _Nullable displayName __attribute__((swift_name("displayName")));
@property (readonly) NISDKBoolean * _Nullable enableDebug __attribute__((swift_name("enableDebug")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FacebookPlatformConfig.Companion")))
@interface NISDKFacebookPlatformConfigCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKFacebookPlatformConfigCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FirebasePlatformConfig")))
@interface NISDKFirebasePlatformConfig : NISDKBase
- (instancetype)initWithCustomEventDisabled:(NISDKBoolean * _Nullable)customEventDisabled __attribute__((swift_name("init(customEventDisabled:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKFirebasePlatformConfigCompanion *companion __attribute__((swift_name("companion")));
- (NISDKFirebasePlatformConfig *)doCopyCustomEventDisabled:(NISDKBoolean * _Nullable)customEventDisabled __attribute__((swift_name("doCopy(customEventDisabled:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKBoolean * _Nullable customEventDisabled __attribute__((swift_name("customEventDisabled")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FirebasePlatformConfig.Companion")))
@interface NISDKFirebasePlatformConfigCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKFirebasePlatformConfigCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NoctuaConfig")))
@interface NISDKNoctuaConfig : NISDKBase
- (instancetype)initWithClientId:(NSString * _Nullable)clientId gameId:(NISDKLong * _Nullable)gameId adjust:(NISDKPlatformAdjustConfig * _Nullable)adjust facebook:(NISDKPlatformFacebookConfig * _Nullable)facebook firebase:(NISDKPlatformFirebaseConfig * _Nullable)firebase noctua:(NISDKNoctuaFeatureConfig * _Nullable)noctua __attribute__((swift_name("init(clientId:gameId:adjust:facebook:firebase:noctua:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKNoctuaConfigCompanion *companion __attribute__((swift_name("companion")));
- (NISDKNoctuaConfig *)doCopyClientId:(NSString * _Nullable)clientId gameId:(NISDKLong * _Nullable)gameId adjust:(NISDKPlatformAdjustConfig * _Nullable)adjust facebook:(NISDKPlatformFacebookConfig * _Nullable)facebook firebase:(NISDKPlatformFirebaseConfig * _Nullable)firebase noctua:(NISDKNoctuaFeatureConfig * _Nullable)noctua __attribute__((swift_name("doCopy(clientId:gameId:adjust:facebook:firebase:noctua:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKPlatformAdjustConfig * _Nullable adjust __attribute__((swift_name("adjust")));
@property (readonly) NSString * _Nullable clientId __attribute__((swift_name("clientId")));
@property (readonly) NISDKPlatformFacebookConfig * _Nullable facebook __attribute__((swift_name("facebook")));
@property (readonly) NISDKPlatformFirebaseConfig * _Nullable firebase __attribute__((swift_name("firebase")));
@property (readonly) NISDKLong * _Nullable gameId __attribute__((swift_name("gameId")));
@property (readonly) NISDKNoctuaFeatureConfig * _Nullable noctua __attribute__((swift_name("noctua")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NoctuaConfig.Companion")))
@interface NISDKNoctuaConfigCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKNoctuaConfigCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NoctuaFeatureConfig")))
@interface NISDKNoctuaFeatureConfig : NISDKBase
- (instancetype)initWithSentryDsnUrl:(NSString * _Nullable)sentryDsnUrl sandboxEnabled:(NISDKBoolean * _Nullable)sandboxEnabled offlineFirstEnabled:(NISDKBoolean * _Nullable)offlineFirstEnabled welcomeToastDisabled:(NISDKBoolean * _Nullable)welcomeToastDisabled iaaEnabled:(NISDKBoolean * _Nullable)iaaEnabled iapDisabled:(NISDKBoolean * _Nullable)iapDisabled remoteFeatureFlags:(NISDKRemoteFeatureFlags * _Nullable)remoteFeatureFlags __attribute__((swift_name("init(sentryDsnUrl:sandboxEnabled:offlineFirstEnabled:welcomeToastDisabled:iaaEnabled:iapDisabled:remoteFeatureFlags:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKNoctuaFeatureConfigCompanion *companion __attribute__((swift_name("companion")));
- (NISDKNoctuaFeatureConfig *)doCopySentryDsnUrl:(NSString * _Nullable)sentryDsnUrl sandboxEnabled:(NISDKBoolean * _Nullable)sandboxEnabled offlineFirstEnabled:(NISDKBoolean * _Nullable)offlineFirstEnabled welcomeToastDisabled:(NISDKBoolean * _Nullable)welcomeToastDisabled iaaEnabled:(NISDKBoolean * _Nullable)iaaEnabled iapDisabled:(NISDKBoolean * _Nullable)iapDisabled remoteFeatureFlags:(NISDKRemoteFeatureFlags * _Nullable)remoteFeatureFlags __attribute__((swift_name("doCopy(sentryDsnUrl:sandboxEnabled:offlineFirstEnabled:welcomeToastDisabled:iaaEnabled:iapDisabled:remoteFeatureFlags:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKBoolean * _Nullable iaaEnabled __attribute__((swift_name("iaaEnabled")));
@property (readonly) NISDKBoolean * _Nullable iapDisabled __attribute__((swift_name("iapDisabled")));
@property (readonly) NISDKBoolean * _Nullable offlineFirstEnabled __attribute__((swift_name("offlineFirstEnabled")));
@property (readonly) NISDKRemoteFeatureFlags * _Nullable remoteFeatureFlags __attribute__((swift_name("remoteFeatureFlags")));
@property (readonly) NISDKBoolean * _Nullable sandboxEnabled __attribute__((swift_name("sandboxEnabled")));
@property (readonly) NSString * _Nullable sentryDsnUrl __attribute__((swift_name("sentryDsnUrl")));
@property (readonly) NISDKBoolean * _Nullable welcomeToastDisabled __attribute__((swift_name("welcomeToastDisabled")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NoctuaFeatureConfig.Companion")))
@interface NISDKNoctuaFeatureConfigCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKNoctuaFeatureConfigCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformAdjustConfig")))
@interface NISDKPlatformAdjustConfig : NISDKBase
- (instancetype)initWithAndroid:(NISDKAdjustPlatformConfig * _Nullable)android ios:(NISDKAdjustPlatformConfig * _Nullable)ios __attribute__((swift_name("init(android:ios:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKPlatformAdjustConfigCompanion *companion __attribute__((swift_name("companion")));
- (NISDKPlatformAdjustConfig *)doCopyAndroid:(NISDKAdjustPlatformConfig * _Nullable)android ios:(NISDKAdjustPlatformConfig * _Nullable)ios __attribute__((swift_name("doCopy(android:ios:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKAdjustPlatformConfig * _Nullable android __attribute__((swift_name("android")));
@property (readonly) NISDKAdjustPlatformConfig * _Nullable ios __attribute__((swift_name("ios")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformAdjustConfig.Companion")))
@interface NISDKPlatformAdjustConfigCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKPlatformAdjustConfigCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformFacebookConfig")))
@interface NISDKPlatformFacebookConfig : NISDKBase
- (instancetype)initWithAndroid:(NISDKFacebookPlatformConfig * _Nullable)android ios:(NISDKFacebookPlatformConfig * _Nullable)ios __attribute__((swift_name("init(android:ios:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKPlatformFacebookConfigCompanion *companion __attribute__((swift_name("companion")));
- (NISDKPlatformFacebookConfig *)doCopyAndroid:(NISDKFacebookPlatformConfig * _Nullable)android ios:(NISDKFacebookPlatformConfig * _Nullable)ios __attribute__((swift_name("doCopy(android:ios:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKFacebookPlatformConfig * _Nullable android __attribute__((swift_name("android")));
@property (readonly) NISDKFacebookPlatformConfig * _Nullable ios __attribute__((swift_name("ios")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformFacebookConfig.Companion")))
@interface NISDKPlatformFacebookConfigCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKPlatformFacebookConfigCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformFirebaseConfig")))
@interface NISDKPlatformFirebaseConfig : NISDKBase
- (instancetype)initWithAndroid:(NISDKFirebasePlatformConfig * _Nullable)android ios:(NISDKFirebasePlatformConfig * _Nullable)ios __attribute__((swift_name("init(android:ios:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKPlatformFirebaseConfigCompanion *companion __attribute__((swift_name("companion")));
- (NISDKPlatformFirebaseConfig *)doCopyAndroid:(NISDKFirebasePlatformConfig * _Nullable)android ios:(NISDKFirebasePlatformConfig * _Nullable)ios __attribute__((swift_name("doCopy(android:ios:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKFirebasePlatformConfig * _Nullable android __attribute__((swift_name("android")));
@property (readonly) NISDKFirebasePlatformConfig * _Nullable ios __attribute__((swift_name("ios")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformFirebaseConfig.Companion")))
@interface NISDKPlatformFirebaseConfigCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKPlatformFirebaseConfigCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RemoteFeatureFlags")))
@interface NISDKRemoteFeatureFlags : NISDKBase
- (instancetype)initWithSsoDisabled:(NISDKBoolean * _Nullable)ssoDisabled vnLegalPurposeEnabled:(NISDKBoolean * _Nullable)vnLegalPurposeEnabled vnLegalPurposeFullKycEnabled:(NISDKBoolean * _Nullable)vnLegalPurposeFullKycEnabled vnLegalPurposePhoneNumberVerificationEnabled:(NISDKBoolean * _Nullable)vnLegalPurposePhoneNumberVerificationEnabled __attribute__((swift_name("init(ssoDisabled:vnLegalPurposeEnabled:vnLegalPurposeFullKycEnabled:vnLegalPurposePhoneNumberVerificationEnabled:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKRemoteFeatureFlagsCompanion *companion __attribute__((swift_name("companion")));
- (NISDKRemoteFeatureFlags *)doCopySsoDisabled:(NISDKBoolean * _Nullable)ssoDisabled vnLegalPurposeEnabled:(NISDKBoolean * _Nullable)vnLegalPurposeEnabled vnLegalPurposeFullKycEnabled:(NISDKBoolean * _Nullable)vnLegalPurposeFullKycEnabled vnLegalPurposePhoneNumberVerificationEnabled:(NISDKBoolean * _Nullable)vnLegalPurposePhoneNumberVerificationEnabled __attribute__((swift_name("doCopy(ssoDisabled:vnLegalPurposeEnabled:vnLegalPurposeFullKycEnabled:vnLegalPurposePhoneNumberVerificationEnabled:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKBoolean * _Nullable ssoDisabled __attribute__((swift_name("ssoDisabled")));
@property (readonly) NISDKBoolean * _Nullable vnLegalPurposeEnabled __attribute__((swift_name("vnLegalPurposeEnabled")));
@property (readonly) NISDKBoolean * _Nullable vnLegalPurposeFullKycEnabled __attribute__((swift_name("vnLegalPurposeFullKycEnabled")));
@property (readonly) NISDKBoolean * _Nullable vnLegalPurposePhoneNumberVerificationEnabled __attribute__((swift_name("vnLegalPurposePhoneNumberVerificationEnabled")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RemoteFeatureFlags.Companion")))
@interface NISDKRemoteFeatureFlagsCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKRemoteFeatureFlagsCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("NetworkStatusProvider")))
@interface NISDKNetworkStatusProvider : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)networkStatusProvider __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKNetworkStatusProvider *shared __attribute__((swift_name("shared")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)isConnectedCacheDuration:(int64_t)cacheDuration completionHandler:(void (^)(NISDKBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("isConnected(cacheDuration:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppContext")))
@interface NISDKAppContext : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)appContext __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKAppContext *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppLogger")))
@interface NISDKAppLogger : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)appLogger __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKAppLogger *shared __attribute__((swift_name("shared")));
- (void)dTag:(NSString *)tag message:(NSString *)message __attribute__((swift_name("d(tag:message:)")));
- (void)eTag:(NSString *)tag message:(NSString *)message throwable:(NISDKKotlinThrowable * _Nullable)throwable __attribute__((swift_name("e(tag:message:throwable:)")));
- (void)iTag:(NSString *)tag message:(NSString *)message __attribute__((swift_name("i(tag:message:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Constants")))
@interface NISDKConstants : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)constants __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKConstants *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *BASE_URL __attribute__((swift_name("BASE_URL")));
@property (readonly) NSString *NOCTUA_TAG __attribute__((swift_name("NOCTUA_TAG")));
@property (readonly) NSString *SDK_VERSION __attribute__((swift_name("SDK_VERSION")));
@end

__attribute__((swift_name("Error")))
@protocol NISDKError
@required
@end

__attribute__((swift_name("DataError")))
@protocol NISDKDataError <NISDKError>
@required
@end

__attribute__((swift_name("KotlinComparable")))
@protocol NISDKKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end

__attribute__((swift_name("KotlinEnum")))
@interface NISDKKotlinEnum<E> : NISDKBase <NISDKKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DataErrorLocal")))
@interface NISDKDataErrorLocal : NISDKKotlinEnum<NISDKDataErrorLocal *> <NISDKDataError>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) NISDKDataErrorLocal *diskFull __attribute__((swift_name("diskFull")));
@property (class, readonly) NISDKDataErrorLocal *unknown __attribute__((swift_name("unknown")));
+ (NISDKKotlinArray<NISDKDataErrorLocal *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<NISDKDataErrorLocal *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DataErrorRemote")))
@interface NISDKDataErrorRemote : NISDKKotlinEnum<NISDKDataErrorRemote *> <NISDKDataError>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) NISDKDataErrorRemote *requestTimeout __attribute__((swift_name("requestTimeout")));
@property (class, readonly) NISDKDataErrorRemote *tooManyRequests __attribute__((swift_name("tooManyRequests")));
@property (class, readonly) NISDKDataErrorRemote *noInternet __attribute__((swift_name("noInternet")));
@property (class, readonly) NISDKDataErrorRemote *server __attribute__((swift_name("server")));
@property (class, readonly) NISDKDataErrorRemote *serialization __attribute__((swift_name("serialization")));
@property (class, readonly) NISDKDataErrorRemote *unknown __attribute__((swift_name("unknown")));
+ (NISDKKotlinArray<NISDKDataErrorRemote *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<NISDKDataErrorRemote *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeviceUtils")))
@interface NISDKDeviceUtils : NISDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (readonly) NSString *acceptLanguage __attribute__((swift_name("acceptLanguage")));
@property (readonly) NSString *bundleId __attribute__((swift_name("bundleId")));
@property (readonly) NSString *country __attribute__((swift_name("country")));
@property (readonly) NSString *currency __attribute__((swift_name("currency")));
@property (readonly) NSString *deviceId __attribute__((swift_name("deviceId")));
@property (readonly) NSString *deviceModel __attribute__((swift_name("deviceModel")));
@property (readonly) NSString *gameVersion __attribute__((swift_name("gameVersion")));
@property (readonly) NSString *language __attribute__((swift_name("language")));
@property (readonly) NSString *osAgent __attribute__((swift_name("osAgent")));
@property (readonly) NSString *platform __attribute__((swift_name("platform")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PlatformType")))
@interface NISDKPlatformType : NISDKKotlinEnum<NISDKPlatformType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) NISDKPlatformType *playstore __attribute__((swift_name("playstore")));
@property (class, readonly) NISDKPlatformType *appstore __attribute__((swift_name("appstore")));
@property (class, readonly) NISDKPlatformType *direct __attribute__((swift_name("direct")));
+ (NISDKKotlinArray<NISDKPlatformType *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<NISDKPlatformType *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((swift_name("Result")))
@protocol NISDKResult
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ResultError")))
@interface NISDKResultError<__covariant E> : NISDKBase <NISDKResult>
- (instancetype)initWithError:(E)error __attribute__((swift_name("init(error:)"))) __attribute__((objc_designated_initializer));
- (NISDKResultError<E> *)doCopyError:(E)error __attribute__((swift_name("doCopy(error:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) E error __attribute__((swift_name("error")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ResultSuccess")))
@interface NISDKResultSuccess<__covariant D> : NISDKBase <NISDKResult>
- (instancetype)initWithData:(D _Nullable)data __attribute__((swift_name("init(data:)"))) __attribute__((objc_designated_initializer));
- (NISDKResultSuccess<D> *)doCopyData:(D _Nullable)data __attribute__((swift_name("doCopy(data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) D _Nullable data __attribute__((swift_name("data")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SessionTrackerConfig")))
@interface NISDKSessionTrackerConfig : NISDKBase
- (instancetype)initWithHeartbeatPeriodMs:(int64_t)heartbeatPeriodMs sessionTimeoutMs:(int64_t)sessionTimeoutMs __attribute__((swift_name("init(heartbeatPeriodMs:sessionTimeoutMs:)"))) __attribute__((objc_designated_initializer));
- (NISDKSessionTrackerConfig *)doCopyHeartbeatPeriodMs:(int64_t)heartbeatPeriodMs sessionTimeoutMs:(int64_t)sessionTimeoutMs __attribute__((swift_name("doCopy(heartbeatPeriodMs:sessionTimeoutMs:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int64_t heartbeatPeriodMs __attribute__((swift_name("heartbeatPeriodMs")));
@property (readonly) int64_t sessionTimeoutMs __attribute__((swift_name("sessionTimeoutMs")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("InitKoinKt")))
@interface NISDKInitKoinKt : NISDKBase
+ (void)doInitKoinConfig:(void (^ _Nullable)(NISDKKoin_coreKoinApplication *))config __attribute__((swift_name("doInitKoin(config:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Modules_iosKt")))
@interface NISDKModules_iosKt : NISDKBase
@property (class, readonly) NISDKKoin_coreModule *platformModule __attribute__((swift_name("platformModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ModulesKt")))
@interface NISDKModulesKt : NISDKBase
@property (class, readonly) NISDKKoin_coreModule *sharedModule __attribute__((swift_name("sharedModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ResultKt")))
@interface NISDKResultKt : NISDKBase
+ (id<NISDKResult>)asEmptyDataResult:(id<NISDKResult>)receiver __attribute__((swift_name("asEmptyDataResult(_:)")));
+ (id<NISDKResult>)map:(id<NISDKResult>)receiver map:(id _Nullable (^)(id _Nullable))map __attribute__((swift_name("map(_:map:)")));
+ (id<NISDKResult>)onError:(id<NISDKResult>)receiver action:(void (^)(id<NISDKError>))action __attribute__((swift_name("onError(_:action:)")));
+ (id<NISDKResult>)onSuccess:(id<NISDKResult>)receiver action:(void (^)(id _Nullable))action __attribute__((swift_name("onSuccess(_:action:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Utils_iosKt")))
@interface NISDKUtils_iosKt : NISDKBase
+ (NSString *)getPlatformType __attribute__((swift_name("getPlatformType()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
+ (void)isNetworkAvailableWithCompletionHandler:(void (^)(NISDKBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("isNetworkAvailable(completionHandler:)")));
+ (NISDKNoctuaConfig *)loadAppConfig __attribute__((swift_name("loadAppConfig()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UtilsKt")))
@interface NISDKUtilsKt : NISDKBase
+ (NSDictionary<NSString *, id> *)additionalParamsDeviceUtils:(NISDKDeviceUtils *)deviceUtils noctuaConfig:(NISDKNoctuaConfig *)noctuaConfig __attribute__((swift_name("additionalParams(deviceUtils:noctuaConfig:)")));
+ (NSString *)getCurrentDateTimestamp __attribute__((swift_name("getCurrentDateTimestamp()")));
+ (int64_t)getCurrentTimeMillis __attribute__((swift_name("getCurrentTimeMillis()")));
+ (NSString *)mapToJsonStringMap:(NSDictionary<NSString *, id> *)map __attribute__((swift_name("mapToJsonString(map:)")));
@property (class, readonly) NISDKKotlinx_serialization_jsonJson *json __attribute__((swift_name("json")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Room_runtimeRoomDatabaseBuilder")))
@interface NISDKRoom_runtimeRoomDatabaseBuilder<T> : NISDKBase
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)addAutoMigrationSpecAutoMigrationSpec:(id<NISDKRoom_runtimeAutoMigrationSpec>)autoMigrationSpec __attribute__((swift_name("addAutoMigrationSpec(autoMigrationSpec:)")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)addCallbackCallback:(NISDKRoom_runtimeRoomDatabaseCallback *)callback __attribute__((swift_name("addCallback(callback:)")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)addMigrationsMigrations:(NISDKKotlinArray<NISDKRoom_runtimeMigration *> *)migrations __attribute__((swift_name("addMigrations(migrations:)")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)addTypeConverterTypeConverter:(id)typeConverter __attribute__((swift_name("addTypeConverter(typeConverter:)")));
- (T)build __attribute__((swift_name("build()")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)fallbackToDestructiveMigrationDropAllTables:(BOOL)dropAllTables __attribute__((swift_name("fallbackToDestructiveMigration(dropAllTables:)")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)fallbackToDestructiveMigrationFromDropAllTables:(BOOL)dropAllTables startVersions:(NISDKKotlinIntArray *)startVersions __attribute__((swift_name("fallbackToDestructiveMigrationFrom(dropAllTables:startVersions:)")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)fallbackToDestructiveMigrationOnDowngradeDropAllTables:(BOOL)dropAllTables __attribute__((swift_name("fallbackToDestructiveMigrationOnDowngrade(dropAllTables:)")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)setDriverDriver:(id<NISDKSqliteSQLiteDriver>)driver __attribute__((swift_name("setDriver(driver:)")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)setJournalModeJournalMode:(NISDKRoom_runtimeRoomDatabaseJournalMode *)journalMode __attribute__((swift_name("setJournalMode(journalMode:)")));
- (NISDKRoom_runtimeRoomDatabaseBuilder<T> *)setQueryCoroutineContextContext:(id<NISDKKotlinCoroutineContext>)context __attribute__((swift_name("setQueryCoroutineContext(context:)")));
@end

__attribute__((swift_name("Room_runtimeMigration")))
@interface NISDKRoom_runtimeMigration : NISDKBase
- (instancetype)initWithStartVersion:(int32_t)startVersion endVersion:(int32_t)endVersion __attribute__((swift_name("init(startVersion:endVersion:)"))) __attribute__((objc_designated_initializer));
- (void)migrateConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("migrate(connection:)")));
@property (readonly) int32_t endVersion __attribute__((swift_name("endVersion")));
@property (readonly) int32_t startVersion __attribute__((swift_name("startVersion")));
@end

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol NISDKKotlinKDeclarationContainer
@required
@end

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol NISDKKotlinKAnnotatedElement
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((swift_name("KotlinKClassifier")))
@protocol NISDKKotlinKClassifier
@required
@end

__attribute__((swift_name("KotlinKClass")))
@protocol NISDKKotlinKClass <NISDKKotlinKDeclarationContainer, NISDKKotlinKAnnotatedElement, NISDKKotlinKClassifier>
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end

__attribute__((swift_name("Room_runtimeAutoMigrationSpec")))
@protocol NISDKRoom_runtimeAutoMigrationSpec
@required
- (void)onPostMigrateConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onPostMigrate(connection:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Room_runtimeInvalidationTracker")))
@interface NISDKRoom_runtimeInvalidationTracker : NISDKBase

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
*/
- (instancetype)initWithDatabase:(NISDKRoom_runtimeRoomDatabase *)database shadowTablesMap:(NSDictionary<NSString *, NSString *> *)shadowTablesMap viewTables:(NSDictionary<NSString *, NSSet<NSString *> *> *)viewTables tableNames:(NISDKKotlinArray<NSString *> *)tableNames __attribute__((swift_name("init(database:shadowTablesMap:viewTables:tableNames:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   kotlin.jvm.JvmOverloads
*/
- (id<NISDKKotlinx_coroutines_coreFlow>)createFlowTables:(NISDKKotlinArray<NSString *> *)tables emitInitialState:(BOOL)emitInitialState __attribute__((swift_name("createFlow(tables:emitInitialState:)")));

/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP])
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)refreshTables:(NISDKKotlinArray<NSString *> *)tables completionHandler:(void (^)(NISDKBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("refresh(tables:completionHandler:)")));
- (void)refreshAsync __attribute__((swift_name("refreshAsync()")));
@end

__attribute__((swift_name("Room_runtimeRoomOpenDelegateMarker")))
@protocol NISDKRoom_runtimeRoomOpenDelegateMarker
@required
@end

__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineScope")))
@protocol NISDKKotlinx_coroutines_coreCoroutineScope
@required
@property (readonly) id<NISDKKotlinCoroutineContext> coroutineContext __attribute__((swift_name("coroutineContext")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="2.0")
*/
__attribute__((swift_name("KotlinAutoCloseable")))
@protocol NISDKKotlinAutoCloseable
@required
- (void)close __attribute__((swift_name("close()")));
@end

__attribute__((swift_name("SqliteSQLiteConnection")))
@protocol NISDKSqliteSQLiteConnection <NISDKKotlinAutoCloseable>
@required
- (id<NISDKSqliteSQLiteStatement>)prepareSql:(NSString *)sql __attribute__((swift_name("prepare(sql:)")));
@end


/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
*/
__attribute__((swift_name("Room_runtimeRoomOpenDelegate")))
@interface NISDKRoom_runtimeRoomOpenDelegate : NISDKBase <NISDKRoom_runtimeRoomOpenDelegateMarker>
- (instancetype)initWithVersion:(int32_t)version identityHash:(NSString *)identityHash legacyIdentityHash:(NSString *)legacyIdentityHash __attribute__((swift_name("init(version:identityHash:legacyIdentityHash:)"))) __attribute__((objc_designated_initializer));
- (void)createAllTablesConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("createAllTables(connection:)")));
- (void)dropAllTablesConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("dropAllTables(connection:)")));
- (void)onCreateConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onCreate(connection:)")));
- (void)onOpenConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onOpen(connection:)")));
- (void)onPostMigrateConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onPostMigrate(connection:)")));
- (void)onPreMigrateConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onPreMigrate(connection:)")));
- (NISDKRoom_runtimeRoomOpenDelegateValidationResult *)onValidateSchemaConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onValidateSchema(connection:)")));
@property (readonly) NSString *identityHash __attribute__((swift_name("identityHash")));
@property (readonly) NSString *legacyIdentityHash __attribute__((swift_name("legacyIdentityHash")));
@property (readonly) int32_t version __attribute__((swift_name("version")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface NISDKKotlinThrowable : NISDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   kotlin.experimental.ExperimentalNativeApi
*/
- (NISDKKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface NISDKKotlinException : NISDKKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface NISDKKotlinRuntimeException : NISDKKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface NISDKKotlinIllegalStateException : NISDKKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface NISDKKotlinCancellationException : NISDKKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(NISDKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol NISDKKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<NISDKKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<NISDKKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol NISDKKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<NISDKKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<NISDKKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol NISDKKotlinx_serialization_coreKSerializer <NISDKKotlinx_serialization_coreSerializationStrategy, NISDKKotlinx_serialization_coreDeserializationStrategy>
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface NISDKKotlinEnumCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface NISDKKotlinArray<T> : NISDKBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(NISDKInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<NISDKKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoinApplication")))
@interface NISDKKoin_coreKoinApplication : NISDKBase
@property (class, readonly, getter=companion) NISDKKoin_coreKoinApplicationCompanion *companion __attribute__((swift_name("companion")));
- (void)allowOverrideOverride:(BOOL)override __attribute__((swift_name("allowOverride(override:)")));
- (void)close __attribute__((swift_name("close()")));
- (void)createEagerInstances __attribute__((swift_name("createEagerInstances()")));
- (NISDKKoin_coreKoinApplication *)loggerLogger:(NISDKKoin_coreLogger *)logger __attribute__((swift_name("logger(logger:)")));
- (NISDKKoin_coreKoinApplication *)modulesModules:(NISDKKotlinArray<NISDKKoin_coreModule *> *)modules __attribute__((swift_name("modules(modules:)")));
- (NISDKKoin_coreKoinApplication *)modulesModules_:(NSArray<NISDKKoin_coreModule *> *)modules __attribute__((swift_name("modules(modules_:)")));
- (NISDKKoin_coreKoinApplication *)modulesModules__:(NISDKKoin_coreModule *)modules __attribute__((swift_name("modules(modules__:)")));
- (NISDKKoin_coreKoinApplication *)printLoggerLevel:(NISDKKoin_coreLevel *)level __attribute__((swift_name("printLogger(level:)")));
- (NISDKKoin_coreKoinApplication *)propertiesValues:(NSDictionary<NSString *, id> *)values __attribute__((swift_name("properties(values:)")));
@property (readonly) NISDKKoin_coreKoin *koin __attribute__((swift_name("koin")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreModule")))
@interface NISDKKoin_coreModule : NISDKBase
- (instancetype)initWith_createdAtStart:(BOOL)_createdAtStart __attribute__((swift_name("init(_createdAtStart:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NISDKKoin_coreKoinDefinition<id> *)factoryQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(NISDKKoin_coreScope *, NISDKKoin_coreParametersHolder *))definition __attribute__((swift_name("factory(qualifier:definition:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (void)includesModule:(NISDKKotlinArray<NISDKKoin_coreModule *> *)module __attribute__((swift_name("includes(module:)")));
- (void)includesModule_:(id)module __attribute__((swift_name("includes(module_:)")));
- (void)indexPrimaryTypeInstanceFactory:(NISDKKoin_coreInstanceFactory<id> *)instanceFactory __attribute__((swift_name("indexPrimaryType(instanceFactory:)")));
- (void)indexSecondaryTypesInstanceFactory:(NISDKKoin_coreInstanceFactory<id> *)instanceFactory __attribute__((swift_name("indexSecondaryTypes(instanceFactory:)")));
- (NSArray<NISDKKoin_coreModule *> *)plusModules:(NSArray<NISDKKoin_coreModule *> *)modules __attribute__((swift_name("plus(modules:)")));
- (NSArray<NISDKKoin_coreModule *> *)plusModule:(NISDKKoin_coreModule *)module __attribute__((swift_name("plus(module:)")));
- (void)prepareForCreationAtStartInstanceFactory:(NISDKKoin_coreSingleInstanceFactory<id> *)instanceFactory __attribute__((swift_name("prepareForCreationAtStart(instanceFactory:)")));
- (void)scopeScopeSet:(void (^)(NISDKKoin_coreScopeDSL *))scopeSet __attribute__((swift_name("scope(scopeSet:)")));
- (void)scopeQualifier:(id<NISDKKoin_coreQualifier>)qualifier scopeSet:(void (^)(NISDKKoin_coreScopeDSL *))scopeSet __attribute__((swift_name("scope(qualifier:scopeSet:)")));
- (NISDKKoin_coreKoinDefinition<id> *)singleQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier createdAtStart:(BOOL)createdAtStart definition:(id _Nullable (^)(NISDKKoin_coreScope *, NISDKKoin_coreParametersHolder *))definition __attribute__((swift_name("single(qualifier:createdAtStart:definition:)")));
@property (readonly) NISDKMutableSet<NISDKKoin_coreSingleInstanceFactory<id> *> *eagerInstances __attribute__((swift_name("eagerInstances")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSMutableArray<NISDKKoin_coreModule *> *includedModules __attribute__((swift_name("includedModules")));
@property (readonly) BOOL isLoaded __attribute__((swift_name("isLoaded")));
@property (readonly) NISDKMutableDictionary<NSString *, NISDKKoin_coreInstanceFactory<id> *> *mappings __attribute__((swift_name("mappings")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialFormat")))
@protocol NISDKKotlinx_serialization_coreSerialFormat
@required
@property (readonly) NISDKKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreStringFormat")))
@protocol NISDKKotlinx_serialization_coreStringFormat <NISDKKotlinx_serialization_coreSerialFormat>
@required
- (id _Nullable)decodeFromStringDeserializer:(id<NISDKKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (NSString *)encodeToStringSerializer:(id<NISDKKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
@end

__attribute__((swift_name("Kotlinx_serialization_jsonJson")))
@interface NISDKKotlinx_serialization_jsonJson : NISDKBase <NISDKKotlinx_serialization_coreStringFormat>
@property (class, readonly, getter=companion) NISDKKotlinx_serialization_jsonJsonDefault *companion __attribute__((swift_name("companion")));
- (id _Nullable)decodeFromJsonElementDeserializer:(id<NISDKKotlinx_serialization_coreDeserializationStrategy>)deserializer element:(NISDKKotlinx_serialization_jsonJsonElement *)element __attribute__((swift_name("decodeFromJsonElement(deserializer:element:)")));
- (id _Nullable)decodeFromStringString:(NSString *)string __attribute__((swift_name("decodeFromString(string:)")));
- (id _Nullable)decodeFromStringDeserializer:(id<NISDKKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (NISDKKotlinx_serialization_jsonJsonElement *)encodeToJsonElementSerializer:(id<NISDKKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToJsonElement(serializer:value:)")));
- (NSString *)encodeToStringValue:(id _Nullable)value __attribute__((swift_name("encodeToString(value:)")));
- (NSString *)encodeToStringSerializer:(id<NISDKKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
- (NISDKKotlinx_serialization_jsonJsonElement *)parseToJsonElementString:(NSString *)string __attribute__((swift_name("parseToJsonElement(string:)")));
@property (readonly) NISDKKotlinx_serialization_jsonJsonConfiguration *configuration __attribute__((swift_name("configuration")));
@property (readonly) NISDKKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Room_runtimeRoomDatabase.Callback")))
@interface NISDKRoom_runtimeRoomDatabaseCallback : NISDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)onCreateConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onCreate(connection:)")));
- (void)onDestructiveMigrationConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onDestructiveMigration(connection:)")));
- (void)onOpenConnection:(id<NISDKSqliteSQLiteConnection>)connection __attribute__((swift_name("onOpen(connection:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinIntArray")))
@interface NISDKKotlinIntArray : NISDKBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(NISDKInt *(^)(NISDKInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int32_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (NISDKKotlinIntIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int32_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("SqliteSQLiteDriver")))
@protocol NISDKSqliteSQLiteDriver
@required
- (id<NISDKSqliteSQLiteConnection>)openFileName:(NSString *)fileName __attribute__((swift_name("open(fileName:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Room_runtimeRoomDatabase.JournalMode")))
@interface NISDKRoom_runtimeRoomDatabaseJournalMode : NISDKKotlinEnum<NISDKRoom_runtimeRoomDatabaseJournalMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) NISDKRoom_runtimeRoomDatabaseJournalMode *truncate __attribute__((swift_name("truncate")));
@property (class, readonly) NISDKRoom_runtimeRoomDatabaseJournalMode *writeAheadLogging __attribute__((swift_name("writeAheadLogging")));
+ (NISDKKotlinArray<NISDKRoom_runtimeRoomDatabaseJournalMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<NISDKRoom_runtimeRoomDatabaseJournalMode *> *entries __attribute__((swift_name("entries")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinCoroutineContext")))
@protocol NISDKKotlinCoroutineContext
@required
- (id _Nullable)foldInitial:(id _Nullable)initial operation:(id _Nullable (^)(id _Nullable, id<NISDKKotlinCoroutineContextElement>))operation __attribute__((swift_name("fold(initial:operation:)")));
- (id<NISDKKotlinCoroutineContextElement> _Nullable)getKey:(id<NISDKKotlinCoroutineContextKey>)key __attribute__((swift_name("get(key:)")));
- (id<NISDKKotlinCoroutineContext>)minusKeyKey:(id<NISDKKotlinCoroutineContextKey>)key __attribute__((swift_name("minusKey(key:)")));
- (id<NISDKKotlinCoroutineContext>)plusContext:(id<NISDKKotlinCoroutineContext>)context __attribute__((swift_name("plus(context:)")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol NISDKKotlinx_coroutines_coreFlow
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<NISDKKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end

__attribute__((swift_name("SqliteSQLiteStatement")))
@protocol NISDKSqliteSQLiteStatement <NISDKKotlinAutoCloseable>
@required

/**
 * @param index annotations androidx.annotation.IntRange(from=1.toLong())
*/
- (void)bindBlobIndex:(int32_t)index value:(NISDKKotlinByteArray *)value __attribute__((swift_name("bindBlob(index:value:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=1.toLong())
*/
- (void)bindBooleanIndex:(int32_t)index value:(BOOL)value __attribute__((swift_name("bindBoolean(index:value:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=1.toLong())
*/
- (void)bindDoubleIndex:(int32_t)index value:(double)value __attribute__((swift_name("bindDouble(index:value:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=1.toLong())
*/
- (void)bindFloatIndex:(int32_t)index value:(float)value __attribute__((swift_name("bindFloat(index:value:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=1.toLong())
*/
- (void)bindIntIndex:(int32_t)index value:(int32_t)value __attribute__((swift_name("bindInt(index:value:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=1.toLong())
*/
- (void)bindLongIndex:(int32_t)index value:(int64_t)value __attribute__((swift_name("bindLong(index:value:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=1.toLong())
*/
- (void)bindNullIndex:(int32_t)index __attribute__((swift_name("bindNull(index:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=1.toLong())
*/
- (void)bindTextIndex:(int32_t)index value:(NSString *)value __attribute__((swift_name("bindText(index:value:)")));
- (void)clearBindings __attribute__((swift_name("clearBindings()")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (NISDKKotlinByteArray *)getBlobIndex:(int32_t)index __attribute__((swift_name("getBlob(index:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (BOOL)getBooleanIndex:(int32_t)index __attribute__((swift_name("getBoolean(index:)")));
- (int32_t)getColumnCount __attribute__((swift_name("getColumnCount()")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (NSString *)getColumnNameIndex:(int32_t)index __attribute__((swift_name("getColumnName(index:)")));
- (NSArray<NSString *> *)getColumnNames __attribute__((swift_name("getColumnNames()")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (int32_t)getColumnTypeIndex:(int32_t)index __attribute__((swift_name("getColumnType(index:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (double)getDoubleIndex:(int32_t)index __attribute__((swift_name("getDouble(index:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (float)getFloatIndex:(int32_t)index __attribute__((swift_name("getFloat(index:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (int32_t)getIntIndex:(int32_t)index __attribute__((swift_name("getInt(index:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (int64_t)getLongIndex:(int32_t)index __attribute__((swift_name("getLong(index:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (NSString *)getTextIndex:(int32_t)index __attribute__((swift_name("getText(index:)")));

/**
 * @param index annotations androidx.annotation.IntRange(from=0.toLong())
*/
- (BOOL)isNullIndex:(int32_t)index __attribute__((swift_name("isNull(index:)")));
- (void)reset __attribute__((swift_name("reset()")));
- (BOOL)step __attribute__((swift_name("step()")));
@end


/**
 * @note annotations
 *   androidx.annotation.RestrictTo(value=[Scope.LIBRARY_GROUP_PREFIX])
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Room_runtimeRoomOpenDelegate.ValidationResult")))
@interface NISDKRoom_runtimeRoomOpenDelegateValidationResult : NISDKBase
- (instancetype)initWithIsValid:(BOOL)isValid expectedFoundMsg:(NSString * _Nullable)expectedFoundMsg __attribute__((swift_name("init(isValid:expectedFoundMsg:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString * _Nullable expectedFoundMsg __attribute__((swift_name("expectedFoundMsg")));
@property (readonly) BOOL isValid __attribute__((swift_name("isValid")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol NISDKKotlinx_serialization_coreEncoder
@required
- (id<NISDKKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<NISDKKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<NISDKKotlinx_serialization_coreEncoder>)encodeInlineDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("encodeInline(descriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNull __attribute__((swift_name("encodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableValueSerializer:(id<NISDKKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<NISDKKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) NISDKKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol NISDKKotlinx_serialization_coreSerialDescriptor
@required
- (NSArray<id<NISDKKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<NISDKKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) NSArray<id<NISDKKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));
@property (readonly) NISDKKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol NISDKKotlinx_serialization_coreDecoder
@required
- (id<NISDKKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<NISDKKotlinx_serialization_coreDecoder>)decodeInlineDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeInline(descriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (NISDKKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<NISDKKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<NISDKKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) NISDKKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol NISDKKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoinApplication.Companion")))
@interface NISDKKoin_coreKoinApplicationCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKKoin_coreKoinApplicationCompanion *shared __attribute__((swift_name("shared")));
- (NISDKKoin_coreKoinApplication *)doInit __attribute__((swift_name("doInit()")));
@end

__attribute__((swift_name("Koin_coreLogger")))
@interface NISDKKoin_coreLogger : NISDKBase
- (instancetype)initWithLevel:(NISDKKoin_coreLevel *)level __attribute__((swift_name("init(level:)"))) __attribute__((objc_designated_initializer));
- (void)debugMsg:(NSString *)msg __attribute__((swift_name("debug(msg:)")));
- (void)displayLevel:(NISDKKoin_coreLevel *)level msg:(NSString *)msg __attribute__((swift_name("display(level:msg:)")));
- (void)errorMsg:(NSString *)msg __attribute__((swift_name("error(msg:)")));
- (void)infoMsg:(NSString *)msg __attribute__((swift_name("info(msg:)")));
- (BOOL)isAtLvl:(NISDKKoin_coreLevel *)lvl __attribute__((swift_name("isAt(lvl:)")));
- (void)logLvl:(NISDKKoin_coreLevel *)lvl msg:(NSString *(^)(void))msg __attribute__((swift_name("log(lvl:msg:)")));
- (void)logLvl:(NISDKKoin_coreLevel *)lvl msg_:(NSString *)msg __attribute__((swift_name("log(lvl:msg_:)")));
- (void)warnMsg:(NSString *)msg __attribute__((swift_name("warn(msg:)")));
@property NISDKKoin_coreLevel *level __attribute__((swift_name("level")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreLevel")))
@interface NISDKKoin_coreLevel : NISDKKotlinEnum<NISDKKoin_coreLevel *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) NISDKKoin_coreLevel *debug __attribute__((swift_name("debug")));
@property (class, readonly) NISDKKoin_coreLevel *info __attribute__((swift_name("info")));
@property (class, readonly) NISDKKoin_coreLevel *warning __attribute__((swift_name("warning")));
@property (class, readonly) NISDKKoin_coreLevel *error __attribute__((swift_name("error")));
@property (class, readonly) NISDKKoin_coreLevel *none __attribute__((swift_name("none")));
+ (NISDKKotlinArray<NISDKKoin_coreLevel *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<NISDKKoin_coreLevel *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoin")))
@interface NISDKKoin_coreKoin : NISDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)close __attribute__((swift_name("close()")));
- (void)createEagerInstances __attribute__((swift_name("createEagerInstances()")));
- (NISDKKoin_coreScope *)createScopeT:(id<NISDKKoin_coreKoinScopeComponent>)t __attribute__((swift_name("createScope(t:)")));
- (NISDKKoin_coreScope *)createScopeScopeId:(NSString *)scopeId __attribute__((swift_name("createScope(scopeId:)")));
- (NISDKKoin_coreScope *)createScopeScopeId:(NSString *)scopeId source:(id _Nullable)source __attribute__((swift_name("createScope(scopeId:source:)")));
- (NISDKKoin_coreScope *)createScopeScopeId:(NSString *)scopeId qualifier:(id<NISDKKoin_coreQualifier>)qualifier source:(id _Nullable)source __attribute__((swift_name("createScope(scopeId:qualifier:source:)")));
- (void)declareInstance:(id _Nullable)instance qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier secondaryTypes:(NSArray<id<NISDKKotlinKClass>> *)secondaryTypes allowOverride:(BOOL)allowOverride __attribute__((swift_name("declare(instance:qualifier:secondaryTypes:allowOverride:)")));
- (void)deletePropertyKey:(NSString *)key __attribute__((swift_name("deleteProperty(key:)")));
- (void)deleteScopeScopeId:(NSString *)scopeId __attribute__((swift_name("deleteScope(scopeId:)")));
- (id)getQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(qualifier:parameters:)")));
- (id _Nullable)getClazz:(id<NISDKKotlinKClass>)clazz qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(clazz:qualifier:parameters:)")));
- (NSArray<id> *)getAll __attribute__((swift_name("getAll()")));
- (NISDKKoin_coreScope *)getOrCreateScopeScopeId:(NSString *)scopeId __attribute__((swift_name("getOrCreateScope(scopeId:)")));
- (NISDKKoin_coreScope *)getOrCreateScopeScopeId:(NSString *)scopeId qualifier:(id<NISDKKoin_coreQualifier>)qualifier source:(id _Nullable)source __attribute__((swift_name("getOrCreateScope(scopeId:qualifier:source:)")));
- (id _Nullable)getOrNullQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(qualifier:parameters:)")));
- (id _Nullable)getOrNullClazz:(id<NISDKKotlinKClass>)clazz qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(clazz:qualifier:parameters:)")));
- (id _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (id)getPropertyKey:(NSString *)key defaultValue:(id)defaultValue __attribute__((swift_name("getProperty(key:defaultValue:)")));
- (NISDKKoin_coreScope *)getScopeScopeId:(NSString *)scopeId __attribute__((swift_name("getScope(scopeId:)")));
- (NISDKKoin_coreScope * _Nullable)getScopeOrNullScopeId:(NSString *)scopeId __attribute__((swift_name("getScopeOrNull(scopeId:)")));
- (id<NISDKKotlinLazy>)injectQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier mode:(NISDKKotlinLazyThreadSafetyMode *)mode parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("inject(qualifier:mode:parameters:)")));
- (id<NISDKKotlinLazy>)injectOrNullQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier mode:(NISDKKotlinLazyThreadSafetyMode *)mode parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("injectOrNull(qualifier:mode:parameters:)")));
- (void)loadModulesModules:(NSArray<NISDKKoin_coreModule *> *)modules allowOverride:(BOOL)allowOverride createEagerInstances:(BOOL)createEagerInstances __attribute__((swift_name("loadModules(modules:allowOverride:createEagerInstances:)")));
- (void)setPropertyKey:(NSString *)key value:(id)value __attribute__((swift_name("setProperty(key:value:)")));
- (void)setupLoggerLogger:(NISDKKoin_coreLogger *)logger __attribute__((swift_name("setupLogger(logger:)")));
- (void)unloadModulesModules:(NSArray<NISDKKoin_coreModule *> *)modules __attribute__((swift_name("unloadModules(modules:)")));
@property (readonly) NISDKKoin_coreExtensionManager *extensionManager __attribute__((swift_name("extensionManager")));
@property (readonly) NISDKKoin_coreInstanceRegistry *instanceRegistry __attribute__((swift_name("instanceRegistry")));
@property (readonly) NISDKKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) NISDKKoin_corePropertyRegistry *propertyRegistry __attribute__((swift_name("propertyRegistry")));
@property (readonly) NISDKKoin_coreScopeRegistry *scopeRegistry __attribute__((swift_name("scopeRegistry")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKoinDefinition")))
@interface NISDKKoin_coreKoinDefinition<R> : NISDKBase
- (instancetype)initWithModule:(NISDKKoin_coreModule *)module factory:(NISDKKoin_coreInstanceFactory<R> *)factory __attribute__((swift_name("init(module:factory:)"))) __attribute__((objc_designated_initializer));
- (NISDKKoin_coreKoinDefinition<R> *)doCopyModule:(NISDKKoin_coreModule *)module factory:(NISDKKoin_coreInstanceFactory<R> *)factory __attribute__((swift_name("doCopy(module:factory:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NISDKKoin_coreInstanceFactory<R> *factory __attribute__((swift_name("factory")));
@property (readonly) NISDKKoin_coreModule *module __attribute__((swift_name("module")));
@end

__attribute__((swift_name("Koin_coreQualifier")))
@protocol NISDKKoin_coreQualifier
@required
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end

__attribute__((swift_name("Koin_coreLockable")))
@interface NISDKKoin_coreLockable : NISDKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScope")))
@interface NISDKKoin_coreScope : NISDKKoin_coreLockable
- (instancetype)initWithScopeQualifier:(id<NISDKKoin_coreQualifier>)scopeQualifier id:(NSString *)id isRoot:(BOOL)isRoot _koin:(NISDKKoin_coreKoin *)_koin __attribute__((swift_name("init(scopeQualifier:id:isRoot:_koin:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)close __attribute__((swift_name("close()")));
- (void)declareInstance:(id _Nullable)instance qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier secondaryTypes:(NSArray<id<NISDKKotlinKClass>> *)secondaryTypes allowOverride:(BOOL)allowOverride __attribute__((swift_name("declare(instance:qualifier:secondaryTypes:allowOverride:)")));
- (id)getQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(qualifier:parameters:)")));
- (id _Nullable)getClazz:(id<NISDKKotlinKClass>)clazz qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("get(clazz:qualifier:parameters:)")));
- (NSArray<id> *)getAll __attribute__((swift_name("getAll()")));
- (NSArray<id> *)getAllClazz:(id<NISDKKotlinKClass>)clazz __attribute__((swift_name("getAll(clazz:)")));
- (NISDKKoin_coreKoin *)getKoin __attribute__((swift_name("getKoin()")));
- (id _Nullable)getOrNullQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(qualifier:parameters:)")));
- (id _Nullable)getOrNullClazz:(id<NISDKKotlinKClass>)clazz qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("getOrNull(clazz:qualifier:parameters:)")));
- (id)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (id)getPropertyKey:(NSString *)key defaultValue:(id)defaultValue __attribute__((swift_name("getProperty(key:defaultValue:)")));
- (id _Nullable)getPropertyOrNullKey:(NSString *)key __attribute__((swift_name("getPropertyOrNull(key:)")));
- (NISDKKoin_coreScope *)getScopeScopeID:(NSString *)scopeID __attribute__((swift_name("getScope(scopeID:)")));
- (id _Nullable)getSource __attribute__((swift_name("getSource()")));
- (id<NISDKKotlinLazy>)injectQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier mode:(NISDKKotlinLazyThreadSafetyMode *)mode parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("inject(qualifier:mode:parameters:)")));
- (id<NISDKKotlinLazy>)injectOrNullQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier mode:(NISDKKotlinLazyThreadSafetyMode *)mode parameters:(NISDKKoin_coreParametersHolder *(^ _Nullable)(void))parameters __attribute__((swift_name("injectOrNull(qualifier:mode:parameters:)")));
- (BOOL)isNotClosed __attribute__((swift_name("isNotClosed()")));
- (void)linkToScopes:(NISDKKotlinArray<NISDKKoin_coreScope *> *)scopes __attribute__((swift_name("linkTo(scopes:)")));
- (void)registerCallbackCallback:(id<NISDKKoin_coreScopeCallback>)callback __attribute__((swift_name("registerCallback(callback:)")));
- (NSString *)description __attribute__((swift_name("description()")));
- (void)unlinkScopes:(NISDKKotlinArray<NISDKKoin_coreScope *> *)scopes __attribute__((swift_name("unlink(scopes:)")));
@property (readonly) BOOL closed __attribute__((swift_name("closed")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) BOOL isRoot __attribute__((swift_name("isRoot")));
@property (readonly) NISDKKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) id<NISDKKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@property id _Nullable sourceValue __attribute__((swift_name("sourceValue")));
@end

__attribute__((swift_name("Koin_coreParametersHolder")))
@interface NISDKKoin_coreParametersHolder : NISDKBase
- (instancetype)initWith_values:(NSMutableArray<id> *)_values useIndexedValues:(NISDKBoolean * _Nullable)useIndexedValues __attribute__((swift_name("init(_values:useIndexedValues:)"))) __attribute__((objc_designated_initializer));
- (NISDKKoin_coreParametersHolder *)addValue:(id)value __attribute__((swift_name("add(value:)")));
- (id _Nullable)component1 __attribute__((swift_name("component1()")));
- (id _Nullable)component2 __attribute__((swift_name("component2()")));
- (id _Nullable)component3 __attribute__((swift_name("component3()")));
- (id _Nullable)component4 __attribute__((swift_name("component4()")));
- (id _Nullable)component5 __attribute__((swift_name("component5()")));
- (id _Nullable)elementAtI:(int32_t)i clazz:(id<NISDKKotlinKClass>)clazz __attribute__((swift_name("elementAt(i:clazz:)")));
- (id)get __attribute__((swift_name("get()")));
- (id _Nullable)getI:(int32_t)i __attribute__((swift_name("get(i:)")));
- (id _Nullable)getOrNull __attribute__((swift_name("getOrNull()")));
- (id _Nullable)getOrNullClazz:(id<NISDKKotlinKClass>)clazz __attribute__((swift_name("getOrNull(clazz:)")));
- (NISDKKoin_coreParametersHolder *)insertIndex:(int32_t)index value:(id)value __attribute__((swift_name("insert(index:value:)")));
- (BOOL)isEmpty __attribute__((swift_name("isEmpty()")));
- (BOOL)isNotEmpty __attribute__((swift_name("isNotEmpty()")));
- (void)setI:(int32_t)i t:(id _Nullable)t __attribute__((swift_name("set(i:t:)")));
- (int32_t)size __attribute__((swift_name("size()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property int32_t index __attribute__((swift_name("index")));
@property (readonly) NISDKBoolean * _Nullable useIndexedValues __attribute__((swift_name("useIndexedValues")));
@property (readonly) NSArray<id> *values __attribute__((swift_name("values")));
@end

__attribute__((swift_name("Koin_coreInstanceFactory")))
@interface NISDKKoin_coreInstanceFactory<T> : NISDKKoin_coreLockable
- (instancetype)initWithBeanDefinition:(NISDKKoin_coreBeanDefinition<T> *)beanDefinition __attribute__((swift_name("init(beanDefinition:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
@property (class, readonly, getter=companion) NISDKKoin_coreInstanceFactoryCompanion *companion __attribute__((swift_name("companion")));
- (T _Nullable)createContext:(NISDKKoin_coreResolutionContext *)context __attribute__((swift_name("create(context:)")));
- (void)dropScope:(NISDKKoin_coreScope * _Nullable)scope __attribute__((swift_name("drop(scope:)")));
- (void)dropAll __attribute__((swift_name("dropAll()")));
- (T _Nullable)getContext:(NISDKKoin_coreResolutionContext *)context __attribute__((swift_name("get(context:)")));
- (BOOL)isCreatedContext:(NISDKKoin_coreResolutionContext * _Nullable)context __attribute__((swift_name("isCreated(context:)")));
@property (readonly) NISDKKoin_coreBeanDefinition<T> *beanDefinition __attribute__((swift_name("beanDefinition")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreSingleInstanceFactory")))
@interface NISDKKoin_coreSingleInstanceFactory<T> : NISDKKoin_coreInstanceFactory<T>
- (instancetype)initWithBeanDefinition:(NISDKKoin_coreBeanDefinition<T> *)beanDefinition __attribute__((swift_name("init(beanDefinition:)"))) __attribute__((objc_designated_initializer));
- (T _Nullable)createContext:(NISDKKoin_coreResolutionContext *)context __attribute__((swift_name("create(context:)")));
- (void)dropScope:(NISDKKoin_coreScope * _Nullable)scope __attribute__((swift_name("drop(scope:)")));
- (void)dropAll __attribute__((swift_name("dropAll()")));
- (T _Nullable)getContext:(NISDKKoin_coreResolutionContext *)context __attribute__((swift_name("get(context:)")));
- (BOOL)isCreatedContext:(NISDKKoin_coreResolutionContext * _Nullable)context __attribute__((swift_name("isCreated(context:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeDSL")))
@interface NISDKKoin_coreScopeDSL : NISDKBase
- (instancetype)initWithScopeQualifier:(id<NISDKKoin_coreQualifier>)scopeQualifier module:(NISDKKoin_coreModule *)module __attribute__((swift_name("init(scopeQualifier:module:)"))) __attribute__((objc_designated_initializer));
- (NISDKKoin_coreKoinDefinition<id> *)factoryQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(NISDKKoin_coreScope *, NISDKKoin_coreParametersHolder *))definition __attribute__((swift_name("factory(qualifier:definition:)")));
- (NISDKKoin_coreKoinDefinition<id> *)scopedQualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier definition:(id _Nullable (^)(NISDKKoin_coreScope *, NISDKKoin_coreParametersHolder *))definition __attribute__((swift_name("scoped(qualifier:definition:)")));
@property (readonly) NISDKKoin_coreModule *module __attribute__((swift_name("module")));
@property (readonly) id<NISDKKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface NISDKKotlinx_serialization_coreSerializersModule : NISDKBase

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)dumpToCollector:(id<NISDKKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<NISDKKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<NISDKKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<NISDKKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<NISDKKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<NISDKKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<NISDKKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<NISDKKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJson.Default")))
@interface NISDKKotlinx_serialization_jsonJsonDefault : NISDKKotlinx_serialization_jsonJson
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)default_ __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKKotlinx_serialization_jsonJsonDefault *shared __attribute__((swift_name("shared")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/serialization/json/JsonElementSerializer))
*/
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement")))
@interface NISDKKotlinx_serialization_jsonJsonElement : NISDKBase
@property (class, readonly, getter=companion) NISDKKotlinx_serialization_jsonJsonElementCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonConfiguration")))
@interface NISDKKotlinx_serialization_jsonJsonConfiguration : NISDKBase
- (NSString *)description __attribute__((swift_name("description()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL allowComments __attribute__((swift_name("allowComments")));
@property (readonly) BOOL allowSpecialFloatingPointValues __attribute__((swift_name("allowSpecialFloatingPointValues")));
@property (readonly) BOOL allowStructuredMapKeys __attribute__((swift_name("allowStructuredMapKeys")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL allowTrailingComma __attribute__((swift_name("allowTrailingComma")));
@property (readonly) NSString *classDiscriminator __attribute__((swift_name("classDiscriminator")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property NISDKKotlinx_serialization_jsonClassDiscriminatorMode *classDiscriminatorMode __attribute__((swift_name("classDiscriminatorMode")));
@property (readonly) BOOL coerceInputValues __attribute__((swift_name("coerceInputValues")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL decodeEnumsCaseInsensitive __attribute__((swift_name("decodeEnumsCaseInsensitive")));
@property (readonly) BOOL encodeDefaults __attribute__((swift_name("encodeDefaults")));
@property (readonly) BOOL explicitNulls __attribute__((swift_name("explicitNulls")));
@property (readonly) BOOL ignoreUnknownKeys __attribute__((swift_name("ignoreUnknownKeys")));
@property (readonly) BOOL isLenient __attribute__((swift_name("isLenient")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) id<NISDKKotlinx_serialization_jsonJsonNamingStrategy> _Nullable namingStrategy __attribute__((swift_name("namingStrategy")));
@property (readonly) BOOL prettyPrint __attribute__((swift_name("prettyPrint")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) NSString *prettyPrintIndent __attribute__((swift_name("prettyPrintIndent")));
@property (readonly) BOOL useAlternativeNames __attribute__((swift_name("useAlternativeNames")));
@property (readonly) BOOL useArrayPolymorphism __attribute__((swift_name("useArrayPolymorphism")));
@end

__attribute__((swift_name("KotlinIntIterator")))
@interface NISDKKotlinIntIterator : NISDKBase <NISDKKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NISDKInt *)next __attribute__((swift_name("next()")));
- (int32_t)nextInt __attribute__((swift_name("nextInt()")));
@end

__attribute__((swift_name("KotlinCoroutineContextElement")))
@protocol NISDKKotlinCoroutineContextElement <NISDKKotlinCoroutineContext>
@required
@property (readonly) id<NISDKKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end

__attribute__((swift_name("KotlinCoroutineContextKey")))
@protocol NISDKKotlinCoroutineContextKey
@required
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol NISDKKotlinx_coroutines_coreFlowCollector
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinByteArray")))
@interface NISDKKotlinByteArray : NISDKBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(NISDKByte *(^)(NISDKInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int8_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (NISDKKotlinByteIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int8_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol NISDKKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<NISDKKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<NISDKKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<NISDKKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) NISDKKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("KotlinAnnotation")))
@protocol NISDKKotlinAnnotation
@required
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface NISDKKotlinx_serialization_coreSerialKind : NISDKBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol NISDKKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<NISDKKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<NISDKKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<NISDKKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) NISDKKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface NISDKKotlinNothing : NISDKBase
@end

__attribute__((swift_name("Koin_coreKoinComponent")))
@protocol NISDKKoin_coreKoinComponent
@required
- (NISDKKoin_coreKoin *)getKoin __attribute__((swift_name("getKoin()")));
@end

__attribute__((swift_name("Koin_coreKoinScopeComponent")))
@protocol NISDKKoin_coreKoinScopeComponent <NISDKKoin_coreKoinComponent>
@required
@property (readonly) NISDKKoin_coreScope *scope __attribute__((swift_name("scope")));
@end

__attribute__((swift_name("KotlinLazy")))
@protocol NISDKKotlinLazy
@required
- (BOOL)isInitialized __attribute__((swift_name("isInitialized()")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinLazyThreadSafetyMode")))
@interface NISDKKotlinLazyThreadSafetyMode : NISDKKotlinEnum<NISDKKotlinLazyThreadSafetyMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) NISDKKotlinLazyThreadSafetyMode *synchronized __attribute__((swift_name("synchronized")));
@property (class, readonly) NISDKKotlinLazyThreadSafetyMode *publication __attribute__((swift_name("publication")));
@property (class, readonly) NISDKKotlinLazyThreadSafetyMode *none __attribute__((swift_name("none")));
+ (NISDKKotlinArray<NISDKKotlinLazyThreadSafetyMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<NISDKKotlinLazyThreadSafetyMode *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreExtensionManager")))
@interface NISDKKoin_coreExtensionManager : NISDKBase
- (instancetype)initWith_koin:(NISDKKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (id<NISDKKoin_coreKoinExtension>)getExtensionId:(NSString *)id __attribute__((swift_name("getExtension(id:)")));
- (id<NISDKKoin_coreKoinExtension> _Nullable)getExtensionOrNullId:(NSString *)id __attribute__((swift_name("getExtensionOrNull(id:)")));
- (void)registerExtensionId:(NSString *)id extension:(id<NISDKKoin_coreKoinExtension>)extension __attribute__((swift_name("registerExtension(id:extension:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceRegistry")))
@interface NISDKKoin_coreInstanceRegistry : NISDKBase
- (instancetype)initWith_koin:(NISDKKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)saveMappingAllowOverride:(BOOL)allowOverride mapping:(NSString *)mapping factory:(NISDKKoin_coreInstanceFactory<id> *)factory logWarning:(BOOL)logWarning __attribute__((swift_name("saveMapping(allowOverride:mapping:factory:logWarning:)")));
- (int32_t)size __attribute__((swift_name("size()")));
@property (readonly) NISDKKoin_coreKoin *_koin __attribute__((swift_name("_koin")));
@property (readonly) NSDictionary<NSString *, NISDKKoin_coreInstanceFactory<id> *> *instances __attribute__((swift_name("instances")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_corePropertyRegistry")))
@interface NISDKKoin_corePropertyRegistry : NISDKBase
- (instancetype)initWith_koin:(NISDKKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
- (void)close __attribute__((swift_name("close()")));
- (void)deletePropertyKey:(NSString *)key __attribute__((swift_name("deleteProperty(key:)")));
- (id _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (void)savePropertiesProperties:(NSDictionary<NSString *, id> *)properties __attribute__((swift_name("saveProperties(properties:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeRegistry")))
@interface NISDKKoin_coreScopeRegistry : NISDKBase
- (instancetype)initWith_koin:(NISDKKoin_coreKoin *)_koin __attribute__((swift_name("init(_koin:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) NISDKKoin_coreScopeRegistryCompanion *companion __attribute__((swift_name("companion")));
- (void)loadScopesModules:(NSSet<NISDKKoin_coreModule *> *)modules __attribute__((swift_name("loadScopes(modules:)")));
@property (readonly) NISDKKoin_coreScope *rootScope __attribute__((swift_name("rootScope")));
@property (readonly) NSSet<id<NISDKKoin_coreQualifier>> *scopeDefinitions __attribute__((swift_name("scopeDefinitions")));
@end

__attribute__((swift_name("Koin_coreScopeCallback")))
@protocol NISDKKoin_coreScopeCallback
@required
- (void)onScopeCloseScope:(NISDKKoin_coreScope *)scope __attribute__((swift_name("onScopeClose(scope:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreBeanDefinition")))
@interface NISDKKoin_coreBeanDefinition<T> : NISDKBase
- (instancetype)initWithScopeQualifier:(id<NISDKKoin_coreQualifier>)scopeQualifier primaryType:(id<NISDKKotlinKClass>)primaryType qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier definition:(T _Nullable (^)(NISDKKoin_coreScope *, NISDKKoin_coreParametersHolder *))definition kind:(NISDKKoin_coreKind *)kind secondaryTypes:(NSArray<id<NISDKKotlinKClass>> *)secondaryTypes __attribute__((swift_name("init(scopeQualifier:primaryType:qualifier:definition:kind:secondaryTypes:)"))) __attribute__((objc_designated_initializer));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (BOOL)hasTypeClazz:(id<NISDKKotlinKClass>)clazz __attribute__((swift_name("hasType(clazz:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (BOOL)isClazz:(id<NISDKKotlinKClass>)clazz qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier scopeDefinition:(id<NISDKKoin_coreQualifier>)scopeDefinition __attribute__((swift_name("is(clazz:qualifier:scopeDefinition:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property NISDKKoin_coreCallbacks<T> *callbacks __attribute__((swift_name("callbacks")));
@property (readonly) T _Nullable (^definition)(NISDKKoin_coreScope *, NISDKKoin_coreParametersHolder *) __attribute__((swift_name("definition")));
@property (readonly) NISDKKoin_coreKind *kind __attribute__((swift_name("kind")));
@property (readonly) id<NISDKKotlinKClass> primaryType __attribute__((swift_name("primaryType")));
@property id<NISDKKoin_coreQualifier> _Nullable qualifier __attribute__((swift_name("qualifier")));
@property (readonly) id<NISDKKoin_coreQualifier> scopeQualifier __attribute__((swift_name("scopeQualifier")));
@property NSArray<id<NISDKKotlinKClass>> *secondaryTypes __attribute__((swift_name("secondaryTypes")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreInstanceFactoryCompanion")))
@interface NISDKKoin_coreInstanceFactoryCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKKoin_coreInstanceFactoryCompanion *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *ERROR_SEPARATOR __attribute__((swift_name("ERROR_SEPARATOR")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreResolutionContext")))
@interface NISDKKoin_coreResolutionContext : NISDKBase
- (instancetype)initWithLogger:(NISDKKoin_coreLogger *)logger scope:(NISDKKoin_coreScope *)scope clazz:(id<NISDKKotlinKClass>)clazz qualifier:(id<NISDKKoin_coreQualifier> _Nullable)qualifier parameters:(NISDKKoin_coreParametersHolder * _Nullable)parameters __attribute__((swift_name("init(logger:scope:clazz:qualifier:parameters:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<NISDKKotlinKClass> clazz __attribute__((swift_name("clazz")));
@property (readonly) NSString *debugTag __attribute__((swift_name("debugTag")));
@property (readonly) NISDKKoin_coreLogger *logger __attribute__((swift_name("logger")));
@property (readonly) NISDKKoin_coreParametersHolder * _Nullable parameters __attribute__((swift_name("parameters")));
@property (readonly) id<NISDKKoin_coreQualifier> _Nullable qualifier __attribute__((swift_name("qualifier")));
@property (readonly) NISDKKoin_coreScope *scope __attribute__((swift_name("scope")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol NISDKKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<NISDKKotlinKClass>)kClass provider:(id<NISDKKotlinx_serialization_coreKSerializer> (^)(NSArray<id<NISDKKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<NISDKKotlinKClass>)kClass serializer:(id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<NISDKKotlinKClass>)baseClass actualClass:(id<NISDKKotlinKClass>)actualClass actualSerializer:(id<NISDKKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<NISDKKotlinKClass>)baseClass defaultDeserializerProvider:(id<NISDKKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultDeserializerProvider:)"))) __attribute__((deprecated("Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer")));
- (void)polymorphicDefaultDeserializerBaseClass:(id<NISDKKotlinKClass>)baseClass defaultDeserializerProvider:(id<NISDKKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefaultDeserializer(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultSerializerBaseClass:(id<NISDKKotlinKClass>)baseClass defaultSerializerProvider:(id<NISDKKotlinx_serialization_coreSerializationStrategy> _Nullable (^)(id))defaultSerializerProvider __attribute__((swift_name("polymorphicDefaultSerializer(baseClass:defaultSerializerProvider:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement.Companion")))
@interface NISDKKotlinx_serialization_jsonJsonElementCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKKotlinx_serialization_jsonJsonElementCompanion *shared __attribute__((swift_name("shared")));
- (id<NISDKKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonClassDiscriminatorMode")))
@interface NISDKKotlinx_serialization_jsonClassDiscriminatorMode : NISDKKotlinEnum<NISDKKotlinx_serialization_jsonClassDiscriminatorMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) NISDKKotlinx_serialization_jsonClassDiscriminatorMode *none __attribute__((swift_name("none")));
@property (class, readonly) NISDKKotlinx_serialization_jsonClassDiscriminatorMode *allJsonObjects __attribute__((swift_name("allJsonObjects")));
@property (class, readonly) NISDKKotlinx_serialization_jsonClassDiscriminatorMode *polymorphic __attribute__((swift_name("polymorphic")));
+ (NISDKKotlinArray<NISDKKotlinx_serialization_jsonClassDiscriminatorMode *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<NISDKKotlinx_serialization_jsonClassDiscriminatorMode *> *entries __attribute__((swift_name("entries")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_jsonJsonNamingStrategy")))
@protocol NISDKKotlinx_serialization_jsonJsonNamingStrategy
@required
- (NSString *)serialNameForJsonDescriptor:(id<NISDKKotlinx_serialization_coreSerialDescriptor>)descriptor elementIndex:(int32_t)elementIndex serialName:(NSString *)serialName __attribute__((swift_name("serialNameForJson(descriptor:elementIndex:serialName:)")));
@end

__attribute__((swift_name("KotlinByteIterator")))
@interface NISDKKotlinByteIterator : NISDKBase <NISDKKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NISDKByte *)next __attribute__((swift_name("next()")));
- (int8_t)nextByte __attribute__((swift_name("nextByte()")));
@end

__attribute__((swift_name("Koin_coreKoinExtension")))
@protocol NISDKKoin_coreKoinExtension
@required
- (void)onClose __attribute__((swift_name("onClose()")));
- (void)onRegisterKoin:(NISDKKoin_coreKoin *)koin __attribute__((swift_name("onRegister(koin:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreScopeRegistry.Companion")))
@interface NISDKKoin_coreScopeRegistryCompanion : NISDKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) NISDKKoin_coreScopeRegistryCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreKind")))
@interface NISDKKoin_coreKind : NISDKKotlinEnum<NISDKKoin_coreKind *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) NISDKKoin_coreKind *singleton __attribute__((swift_name("singleton")));
@property (class, readonly) NISDKKoin_coreKind *factory __attribute__((swift_name("factory")));
@property (class, readonly) NISDKKoin_coreKind *scoped __attribute__((swift_name("scoped")));
+ (NISDKKotlinArray<NISDKKoin_coreKind *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<NISDKKoin_coreKind *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Koin_coreCallbacks")))
@interface NISDKKoin_coreCallbacks<T> : NISDKBase
- (instancetype)initWithOnClose:(void (^ _Nullable)(T _Nullable))onClose __attribute__((swift_name("init(onClose:)"))) __attribute__((objc_designated_initializer));
- (NISDKKoin_coreCallbacks<T> *)doCopyOnClose:(void (^ _Nullable)(T _Nullable))onClose __attribute__((swift_name("doCopy(onClose:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) void (^ _Nullable onClose)(T _Nullable) __attribute__((swift_name("onClose")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
