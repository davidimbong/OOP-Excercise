import kotlin.math.exp

class CreditCard(
    firstname: String,
    middlename: String?,
    lastname: String,
    currency: String,
    balance: String,
    dateOfBirth: String,
    val cardNumber: String,
    val cvv: String,
    val expiryDate: String,
    val availableBalance: String,
    val creditLimit: String
) :
    Account(firstname, middlename, lastname, currency, balance, dateOfBirth) {

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

        val formattedBalance = availableBalance.convertToMoneyFormat(currency)
        val formattedLimit = creditLimit.convertToMoneyFormat(currency)

        val balanceOutOf = "$formattedBalance out of $formattedLimit"

        val formattedExpiryD = expiryDate.separateString(2, '/')

        println("$formattedCardNumber | $fullname | Credit Card | $balanceOutOf | $formattedExpiryD")
    }
}