import gg.noctua.analytics.data.models.EventResponse
import gg.noctua.analytics.domain.NoctuaAnalyticsInterface
import gg.noctua.analytics.domain.NoctuaEventAdRevenue
import gg.noctua.analytics.domain.NoctuaEventCustom
import gg.noctua.analytics.domain.NoctuaEventPurchase
import gg.noctua.analytics.utils.DataError
import gg.noctua.analytics.utils.DeviceUtils
import gg.noctua.analytics.utils.Result

class FakeDeviceUtils : DeviceUtils {
    override val platform = "test"
    override val deviceId = "test-device-id"
    override val gameVersion = "1.0.0"
    override val deviceModel = "TestModel"
}

class FakeAnalytics : NoctuaAnalyticsInterface {
    var customEvent: NoctuaEventCustom? = null

    override suspend fun trackCustomEvent(event: NoctuaEventCustom): Result<EventResponse, DataError.Remote> {
        customEvent = event
        return Result.Success(EventResponse("Success")) // return dummy response
    }

    override suspend fun trackPurchase(event: NoctuaEventPurchase) = TODO()
    override suspend fun trackAdRevenue(event: NoctuaEventAdRevenue) = TODO()
    override suspend fun flushEvents() = TODO()
}
