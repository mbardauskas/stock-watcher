package main.kotlin.StockWatcher

import java.net.URL

class Api {
    fun getStocks(): List<Stock> {
        val response = URL("https://financialmodelingprep.com/api/v3/quote/AAPL,FB,WIX").readText()
        return ResponseReader().parseStockList(response)
    }
}