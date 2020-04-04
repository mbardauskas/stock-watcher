package main.kotlin.stockWatcher

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions

class WatcherIntegrationTest {
    @Test
    fun shouldReportChanges() {
        val reporter = TestReporter()
        val api = TestApi()
        val watcher = Watcher(reporter, 2, api)
        api.setPriceForEachStock(104.1)
        watcher.watchSymbols(listOf("AAPL"))

        Assertions.assertThat(api.getStocksCalledTimes()).isEqualTo(1)
        Assertions.assertThat(reporter.isSendReportCalled()).isEqualTo(false)

        api.setPriceForEachStock(110.0)
        Thread.sleep(2*1000+100)

        Assertions.assertThat(api.getStocksCalledTimes()).isEqualTo(2)
        Assertions.assertThat(reporter.isSendReportCalled()).isEqualTo(true)
    }

    @Test
    fun shouldNotReportChangesWhenChangesIsNotImportant() {
        val reporter = TestReporter()
        val api = TestApi()
        val watcher = Watcher(reporter, 2, api)
        api.setPriceForEachStock(105.0)
        watcher.watchSymbols(listOf("AAPL"))

        Assertions.assertThat(api.getStocksCalledTimes()).isEqualTo(1)
        Assertions.assertThat(reporter.isSendReportCalled()).isEqualTo(false)

        api.setPriceForEachStock(109.0)
        Thread.sleep(2*1000+100)

        Assertions.assertThat(api.getStocksCalledTimes()).isEqualTo(2)
        Assertions.assertThat(reporter.isSendReportCalled()).isEqualTo(false)
    }
}

class WatcherHelpersTest {
    @Test
    fun getsNearestTo5() {
        Assertions.assertThat(roundTo5(74.2)).isEqualTo(70.0)
        Assertions.assertThat(roundTo5(76.2)).isEqualTo(75.0)
    }

    @Test
    fun shouldUpdateIfExistingIsSmallerByMoreThan5() {
        Assertions.assertThat(shouldBeUpdated(70.0, 76.1)).isEqualTo(true)
        Assertions.assertThat(shouldBeUpdated(75.0, 76.2)).isEqualTo(false)
    }
}