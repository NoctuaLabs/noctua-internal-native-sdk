import gg.noctua.analytics.presenter.NoctuaAnalyticsPresenter
import gg.noctua.analytics.domain.NoctuaAnalyticsInterface
import gg.noctua.analytics.utils.DeviceUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NoctuaAnalyticsPresenterTest {

    private lateinit var presenter: NoctuaAnalyticsPresenter
    private lateinit var fakeAnalytics: FakeAnalytics

    @BeforeTest
    fun setup() {
        fakeAnalytics = FakeAnalytics()

        startKoin {
            modules(
                module {
                    single<NoctuaAnalyticsInterface> { fakeAnalytics }
                    single<DeviceUtils> { FakeDeviceUtils() }
                }
            )
        }

        presenter = NoctuaAnalyticsPresenter()
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testTrackCustomEvent() = runTest {
        presenter.trackCustomEvent("test_event", mapOf("key" to "value"))

        // Delay briefly to allow coroutine to run (not needed in real coroutine test dispatcher)
        kotlinx.coroutines.delay(100)

        assertEquals("test_event", fakeAnalytics.customEvent?.name)
        assertEquals("value", fakeAnalytics.customEvent?.properties?.get("key"))
    }
}
