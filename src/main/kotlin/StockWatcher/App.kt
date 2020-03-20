package main.kotlin.StockWatcher

fun main(args: Array<String>) {
    val mailServerHost = args.get(0)
    val user = args.get(1)
    val password = args.get(2)
    val recipient = args.get(3)
    val reporter = Reporter(mailServerHost, user, password, recipient)

    val watcher = Watcher(reporter, 10, Api())
    watcher.watchSymbols(listOf("WIX"))
}