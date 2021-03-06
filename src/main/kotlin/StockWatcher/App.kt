package main.kotlin.stockWatcher

fun main(args: Array<String>) {
    val mailServerHost = args[0]
    val user = args[1]
    val password = args[2]
    val recipient = args[3]
    val reporter = EmailReporter(mailServerHost, user, password, recipient)

    // @TODO: Add logger

    val watcher = Watcher(reporter, 10, Api())
    watcher.watchSymbols(listOf("WIX"))
}