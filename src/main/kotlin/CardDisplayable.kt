interface CardDisplayable {

    val cardNumber: String
    val expiryDate: String
    val currency: String

    fun printCardDetails() {
        val formattedCardNumber = cardNumber.separateString(4, ' ')
        val formattedExpiryDate = expiryDate.separateString(2, '/')
        println("$formattedCardNumber : $formattedExpiryDate")
    }
}