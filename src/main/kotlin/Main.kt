fun main(args: Array<String>) {

    var list = listOf<Account>(
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
            cardNumber = "1234567899990000"
        ),
        Savings(
            firstname = "Antonio",
            middlename = "San Andres",
            lastname = "Alkalde",
            currency = "PHP",
            balance = "12523.62",
            dateOfBirth = "2004/04/01",
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
            cardNumber = "9424206544123456"
        ),
        PrepaidCard(
            firstname = "Sherwin",
            middlename = null,
            lastname = "Catli",
            currency = "PHP",
            balance = "12345.01",
            dateOfBirth = "1995/04/11",
            cardNumber = "9215938644987654"
        ),
    )

    list.forEach { it.getFormattedDetails() }
}