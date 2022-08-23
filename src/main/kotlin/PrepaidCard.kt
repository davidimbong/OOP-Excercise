import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.roundToInt

class PrepaidCard(
    firstname: String,
    middlename: String?,
    lastname: String,
    currency: String,
    balance: String,
    dateOfBirth: String,
    cardNumber: String
) :
    Account(firstname, middlename, lastname, currency, balance, dateOfBirth) {

    private val tempNumber = cardNumber

    override fun getFormattedDetails() {

        val formattedCardNumber: StringBuilder = java.lang.StringBuilder("").apply {
            tempNumber.mapIndexed { index, c ->
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
                append(middlename.get(0))

                if (middlename.contains(' ')) {
                    middlename.forEachIndexed { index, c ->
                        if (c.equals(' ')) {
                            append(middlename.get(index + 1).uppercaseChar())
                        }
                    }
                }
            }
        }.toString()

        val bal = balance.toBigDecimal().setScale(2, RoundingMode.DOWN)
        val formattedBalance = DecimalFormat("#,##0.00").format(bal)

        println("$formattedCardNumber | $fullname | Prepaid Card | $currency $formattedBalance | ${if (isMinor) "MINOR" else "NON-MINOR"}")
    }


}