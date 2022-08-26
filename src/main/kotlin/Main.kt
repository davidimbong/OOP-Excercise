import kotlin.math.exp

fun main(args: Array<String>) {

    val list = listOf<Account>(
        Savings(
            firstname = "Juan",
            middlename = "De la Cerna",
            lastname = "De la Cruz",
            currency = "PHP",
            balance = "1000.0",
            dateOfBirth = "2005/01/01",
            accountNumber = "6001234567"
        ),
        PrepaidCard(
            firstname = "Janine",
            middlename = "Santos",
            lastname = "De Leon",
            currency = "USD",
            balance = "100000.0",
            dateOfBirth = "2000/12/25",
            cardNumber = "1234567899990000",
            expiryDate = "1222"
        ),
        Savings(
            firstname = "Antonio",
            middlename = "San Andres",
            lastname = "Alkalde",
            currency = "PHP",
            balance = "12523.62",
            dateOfBirth = "2004/08/29",
            accountNumber = "6002444422"
        ),
        Savings(
            firstname = "Floyd Richard",
            middlename = "Verdejo",
            lastname = "Tiu",
            currency = "PHP",
            balance = "420000.69",
            dateOfBirth = "2001/05/30",
            accountNumber = "0915047069"
        ),
        PrepaidCard(
            firstname = "Carlos",
            middlename = "Wu",
            lastname = "Shi",
            currency = "USD",
            balance = "69000.42",
            dateOfBirth = "2000/11/02",
            cardNumber = "9424206544123456",
            expiryDate = "1223"
        ),
        PrepaidCard(
            firstname = "Sherwin",
            middlename = null,
            lastname = "Catli",
            currency = "PHP",
            balance = "12345.01",
            dateOfBirth = "1995/04/11",
            cardNumber = "9215938644987654",
            expiryDate = "0924"
        ),
        CreditCard(
            firstname = "David Maxwell Jai",
            middlename = "Dotillos",
            lastname = "Imbong",
            currency = "PHP",
            balance = "12036.08",
            dateOfBirth = "2000/05/18",
            cardNumber = "0921593864401234",
            cvv = "563",
            availableBalance = "17963.92",
            creditLimit = "30000",
            expiryDate = "0723"
        ),
        CreditCard(
            firstname = "Time",
            middlename = "Cook",
            lastname = "Apple",
            currency = "PHP",
            balance = "50000.0",
            dateOfBirth = "2000/02/28",
            cardNumber = "4126567899991991",
            cvv = "693",
            availableBalance = "250000.0",
            creditLimit = "300000",
            expiryDate = "0425"
        )
    )//.forEach { it.getFormattedDetails() }

    val list2 = mutableListOf<CardDisplayable>().apply {
        list.filterIsInstance<CardDisplayable>().forEach{
            add(it)
        }
    }.forEach { it.printCardDetails() }
}