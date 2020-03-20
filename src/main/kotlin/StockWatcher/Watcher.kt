package main.kotlin.StockWatcher

import kotlin.concurrent.fixedRateTimer


class Watcher (val reporter: Reporter, val intervalInSeconds: Int, val api: Api) {
    fun watchSymbols(symbols: List<String>) {
        val interval = intervalInSeconds*1000.toLong()
        val initialDelay = 0.toLong()
        fixedRateTimer("StockWatcher", false, initialDelay, interval) {
            val stocks = api.getStocks(symbols)
            val wixStock = stocks.get(0)
            if (wixStock.price > 100) {
                reporter.sendPrice(wixStock.symbol, wixStock.price.toString())
            }
        }
    }
}