package main.kotlin.stockWatcher

class TestReporter : Reporter {
    private var isSendReportCalled = false
    override fun sendReport(symbol: String, price: String) {
        isSendReportCalled = true
    }

    fun isSendReportCalled(): Boolean {
        return isSendReportCalled
    }
}