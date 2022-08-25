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
) :
    Account(firstname, middlename, lastname, currency, balance, dateOfBirth) {

    override fun getFormattedDetails() {

        val formattedCardNumber = StringHelper.separateString(cardNumber, 4)

        val fullname = StringBuilder().apply {
            append(lastname)
            append(", ")
            append(firstname)

            if (!middlename.isNullOrBlank()) {
                append(" ")
                append(StringHelper.getFirstCharPerWord(middlename!!).uppercase())
            }
        }.toString()

        val formattedBalance = StringHelper.convertToBigDecimal(balance)

        println("$formattedCardNumber | $fullname | Prepaid Card | $currency $formattedBalance | ${if (isMinor) "MINOR" else "NON-MINOR"}")
    }


}