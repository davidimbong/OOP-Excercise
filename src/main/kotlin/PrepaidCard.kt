class PrepaidCard(
    firstname: String,
    middlename: String?,
    lastname: String,
    currency: String,
    balance: String,
    dateOfBirth: String,
    private val cardNumber: String
) : Account(firstname, middlename, lastname, currency, balance, dateOfBirth) {

    override fun getFormattedDetails() {

        val formattedCardNumber = cardNumber.separateString(4, ' ')
        val formattedBalance = balance.convertToMoneyFormat(currency)

        println("$formattedCardNumber | $fullname | Prepaid Card | $formattedBalance | $isMinor")
    }


}