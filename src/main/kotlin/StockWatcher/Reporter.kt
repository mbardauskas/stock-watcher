package main.kotlin.stockWatcher

import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class Reporter(private val host: String, val username: String, val password: String, private val recipient: String) {
    private fun getSession(): Session {
        val prop: Properties = System.getProperties()
        prop.setProperty("mail.smtp.host", host)
        prop.setProperty("mail.smtp.port", "465")
        prop.setProperty("mail.smtp.auth", "true")
        prop.setProperty("mail.smtp.socketFactory.port", "465")
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")

        return Session.getInstance(prop,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(username, password)
                }
            })
    }
    fun sendPrice(symbol: String, price: String) {
        val message: Message = MimeMessage(getSession())

        try {
            message.setFrom(InternetAddress(username))
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipient, false)
            )
            message.subject = "Stock price: $symbol $price"
            message.setText("Stock price: $symbol $price")
            Transport.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}