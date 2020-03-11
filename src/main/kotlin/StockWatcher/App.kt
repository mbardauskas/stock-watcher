package main.kotlin.StockWatcher

fun main() {
    val stocks = Api().getStocks();
    println(stocks)
}