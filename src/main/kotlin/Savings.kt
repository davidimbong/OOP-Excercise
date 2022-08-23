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

        val formattedAccNumber: StringBuilder = java.lang.StringBuilder().apply {
            accountNumber.forEachIndexed() { index, c ->
                if ((index) % 4 == 0 && index != 0) {
                    append(" ")
                }
                append(c)
            }
        }

        val fullname = StringBuilder().apply {
            append(lastname)
            append(", ")
            append(firstname)

            if (!middlename.isNullOrBlank()) {
                append(" ")
                append(middlename[0])

                if (middlename.contains(' ')) {
                    middlename.forEachIndexed { index, c ->
                        if (c == ' ') {
                            append(middlename[index + 1].uppercaseChar())
                        }
                    }
                }
            }
        }.toString()

        val bal = balance.toBigDecimal().setScale(2, RoundingMode.DOWN)
        val formattedBalance = DecimalFormat("#,##0.00").format(bal)

        println("$formattedAccNumber | $fullname | Savings Account | $currency $formattedBalance | ${if (isMinor) "MINOR" else "NON-MINOR"}")
    }

}