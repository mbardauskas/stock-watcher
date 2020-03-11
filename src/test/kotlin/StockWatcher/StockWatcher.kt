import main.kotlin.StockWatcher.Stock
import main.kotlin.StockWatcher.ResponseReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResponseReaderTest {
    @Test
    fun ReturnsStockListWithThreeItemsFromActualResponse() {
        val uut = ResponseReader()
        assertThat(
            uut.parseStockList(getThreeFullTestCases())
        ).isEqualTo(
            listOf(
                Stock("AAPL", "Apple Inc.", 285.34000000),
                Stock("FB", "Facebook, Inc.", 178.19000000),
                Stock("WIX", "Wix.com Ltd.", 120.90000000)
            )
        )
    }
}

fun getThreeFullTestCases(): String {
    return """[ {
  "symbol" : "AAPL",
  "name" : "Apple Inc.",
  "price" : 285.34000000,
  "changesPercentage" : 7.20000000,
  "change" : 19.17000000,
  "dayLow" : 269.39000000,
  "dayHigh" : 285.69000000,
  "yearHigh" : 327.85000000,
  "yearLow" : 170.27000000,
  "marketCap" : 1248499335168.00000000,
  "priceAvg50" : 308.41827000,
  "priceAvg200" : 267.29102000,
  "volume" : 65909746,
  "avgVolume" : 39070654,
  "exhange" : "NASDAQ",
  "open" : 277.14000000,
  "previousClose" : 266.17000000,
  "eps" : 12.59500000,
  "pe" : 22.65502200,
  "earningsAnnouncement" : "2020-01-28T21:30:00.000+0000",
  "sharesOutstanding" : 4375479808,
  "timestamp" : 1583930199
}, {
  "symbol" : "FB",
  "name" : "Facebook, Inc.",
  "price" : 178.19000000,
  "changesPercentage" : 5.13000000,
  "change" : 8.69000000,
  "dayLow" : 169.50000000,
  "dayHigh" : 178.29000000,
  "yearHigh" : 224.20000000,
  "yearLow" : 159.28000000,
  "marketCap" : 507921661952.00000000,
  "priceAvg50" : 205.09800000,
  "priceAvg200" : 197.10074000,
  "volume" : 24517791,
  "avgVolume" : 17056954,
  "exhange" : "NASDAQ",
  "open" : 174.67000000,
  "previousClose" : 169.50000000,
  "eps" : 6.43000000,
  "pe" : 27.71228800,
  "earningsAnnouncement" : "2020-01-29T21:05:04.000+0000",
  "sharesOutstanding" : 2405750016,
  "timestamp" : 1583930199
}, {
  "symbol" : "WIX",
  "name" : "Wix.com Ltd.",
  "price" : 120.90000000,
  "changesPercentage" : 4.14000000,
  "change" : 4.81000000,
  "dayLow" : 113.53010000,
  "dayHigh" : 121.70700000,
  "yearHigh" : 156.39700000,
  "yearLow" : 106.28000000,
  "marketCap" : 6251001344.00000000,
  "priceAvg50" : 140.37943000,
  "priceAvg200" : 129.49048000,
  "volume" : 1065639,
  "avgVolume" : 673873,
  "exhange" : "NASDAQ",
  "open" : 120.05000000,
  "previousClose" : 116.09000000,
  "eps" : -1.41400000,
  "pe" : null,
  "earningsAnnouncement" : "2020-02-20T06:00:05.000+0000",
  "sharesOutstanding" : 51151900,
  "timestamp" : 1583930199
} ]"""
}