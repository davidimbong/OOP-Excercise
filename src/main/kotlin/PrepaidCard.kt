class PrepaidCard(
    firstname: String,
    middlename: String?,
    lastname: String,
    currency: String,
    balance: String,
    dateOfBirth: String,
    override val cardNumber: String,
    override val expiryDate: String
) : Account(firstname, middlename, lastname, currency, balance, dateOfBirth), CardDisplayable {

    override fun getFormattedDetails() {

        val formattedCardNumber = cardNumber.separateString(4, ' ')

        println("$formattedCardNumber | $fullname | Prepaid Card | $formattedBalance | $isMinor")
    }


}