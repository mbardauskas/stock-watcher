package main.kotlin.stockWatcher

import java.net.URL

interface StockApi {
    fun getStocks(symbols: List<String>): List<Stock>
}

class Api: StockApi {
    override fun getStocks(symbols: List<String>): List<Stock> {
        val urlArgs = symbols.joinToString(",")
        val response = URL("https://financialmodelingprep.com/api/v3/quote/${urlArgs}").readText()
        return ResponseReader().parseStockList(response)
    }
}