import java.math.RoundingMode
import java.text.DecimalFormat

class PrepaidCard(
    firstname: String,
    middlename: String?,
    lastname: String,
    currency: String,
    balance: String,
    dateOfBirth: String,
    val cardNumber: String
) : Account(firstname, middlename, lastname, currency, balance, dateOfBirth) {

    override fun getFormattedDetails() {

        val formattedCardNumber = cardNumber.separateString(4, ' ')

        val fullname = StringBuilder().apply {
            append(lastname)
            append(", ")
            append(firstname)

            if (!middlename.isNullOrBlank()) {
                append(" ")
                append(middlename.getFirstCharPerWord())
            }
        }.toString()

        val formattedBalance = balance.convertToMoneyFormat(currency)

        println("$formattedCardNumber | $fullname | Prepaid Card | $formattedBalance | ${if (isMinor) "MINOR" else "NON-MINOR"}")
    }


}