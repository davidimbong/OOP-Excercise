import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface CardDisplayable {

    val cardNumber: String
    val expiryDate: String
    val currency: String

    fun printCardDetails() {
        val formattedCardNumber = cardNumber.separateString(4, ' ')
        println("$formattedCardNumber : $expiryDate")
    }
}