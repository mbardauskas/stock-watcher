package main.kotlin.stockWatcher

class TestApi : StockApi {
    private var savedPrice: Double = 100.0
    private var getStocksCalledTimes: Int = 0
    fun setPriceForEachStock(price: Double) {
        savedPrice = price
    }
    override fun getStocks(symbols: List<String>): List<Stock> {
        getStocksCalledTimes++
        val stocks: MutableList<Stock> = mutableListOf()
        symbols.forEach {
            stocks.add(Stock(symbol = it, name = it, price = savedPrice))
        }
        return stocks.toList()
    }

    fun getStocksCalledTimes(): Int {
        return getStocksCalledTimes
    }
}