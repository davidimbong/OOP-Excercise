class Savings(
    firstname: String,
    middlename: String?,
    lastname: String,
    currency: String,
    balance: String,
    dateOfBirth: String,
    private val accountNumber: String
) : Account(firstname, middlename, lastname, currency, balance, dateOfBirth) {
    override fun getFormattedDetails() {

        val formattedAccNumber = accountNumber.separateString(4, ' ')

        println("$formattedAccNumber | $fullname | Savings Account | $formattedBalance | $isMinor")
    }

}