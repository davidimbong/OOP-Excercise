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

        val formattedAccNumber = accountNumber.separateString(4, ' ')

        val formattedBalance = balance.convertToMoneyFormat(currency)

        println("$formattedAccNumber | $fullname | Savings Account | $formattedBalance | $isMinor")
    }

}