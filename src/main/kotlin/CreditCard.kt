class CreditCard(
    firstname: String,
    middlename: String?,
    lastname: String,
    currency: String,
    balance: String,
    dateOfBirth: String,
    override val cardNumber: String,
    private val cvv: String,
    override val expiryDate: String,
    private val availableBalance: String,
    private val creditLimit: String
) : Account(firstname, middlename, lastname, currency, balance, dateOfBirth), CardDisplayable {

    override fun getFormattedDetails() {

        val formattedCardNumber = cardNumber.separateString(4, ' ')
        val formattedLimit = creditLimit.convertToMoneyFormat(currency)
        val formattedExpiryD = expiryDate.separateString(2, '/')

        println("$formattedCardNumber |$fullname | Credit Card | $formattedBalance out of $formattedLimit | $formattedExpiryD")
    }
}