package main.kotlin.stockWatcher

import kotlin.concurrent.fixedRateTimer


class Watcher (private val reporter: Reporter, private val intervalInSeconds: Int, private val api: Api) {
    private val trackedSymbols: MutableMap<String, Double> = mutableMapOf()
    fun watchSymbols(symbols: List<String>) {
        val interval = intervalInSeconds*1000.toLong()
        initCurrentValues(symbols)
        fixedRateTimer("StockWatcher", false, interval, interval) {
            compareAndReportChanges(api.getStocks(symbols))
        }
    }

    private fun initCurrentValues(symbols: List<String>) {
        addTrackedSymbols(api.getStocks(symbols))
    }

    private fun compareAndReportChanges(stocks: List<Stock>) {
        stocks.forEach {
            val currentPrice = trackedSymbols[it.symbol]!!
            if (shouldBeUpdated(currentPrice, it.price)) {
                trackedSymbols[it.symbol] = roundTo5(it.price)
                reporter.sendPrice(symbol = it.symbol, price = it.price.toString())
            }
        }
    }

    private fun addTrackedSymbols(stocks: List<Stock>) {
        stocks.forEach {
            val roundedPrice = roundTo5(it.price)
            if (!trackedSymbols.containsKey(it.symbol)) {
                trackedSymbols[it.symbol] = roundedPrice
            }
        }
    }
}

fun shouldBeUpdated(existing: Double, new: Double): Boolean {
    return (existing + 5.0).compareTo(new) < 0
}

fun roundTo5(num: Double): Double {
    return 5*(Math.floor(Math.abs(num/5)))
}