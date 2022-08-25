import java.math.RoundingMode
import java.text.DecimalFormat

class Savings(
    firstname: String,
    middlename: String?,
    lastname: String,
    currency: String,
    balance: String,
    dateOfBirth: String,
    val accountNumber: String
) :
    Account(firstname, middlename, lastname, currency, balance, dateOfBirth) {
    override fun getFormattedDetails() {

        val formattedAccNumber = StringHelper.separateString(accountNumber, 4)

        val fullname = StringBuilder().apply {
            append(lastname)
            append(", ")
            append(firstname)

            if (!middlename.isNullOrBlank())
                append(" ")
                append(StringHelper.getFirstCharPerWord(middlename!!).uppercase())

        }.toString()

        val formattedBalance = StringHelper.convertToBigDecimal(balance)

        println("$formattedAccNumber | $fullname | Savings Account | $currency $formattedBalance | ${if (isMinor) "MINOR" else "NON-MINOR"}")
    }

}