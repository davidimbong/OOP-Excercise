fun main(args: Array<String>) {

    var list = listOf<Account>(
        Savings("Juan", null, "De la Cruz", "PHP", "1000.0f", "2005/01/01", 6001234567),
        PrepaidCard("Janine", "Santos", "De Leon", "USD", "100000.0f", "2000/12/25", 1234567899990000),
        Savings("Antonio", "San Andres", "Alkalde", "PHP", "12523.62f", "2004/04/01", 6002444422),
        Savings("Floyd Richard", "Verdejo", "Tiu", "PHP", "420000.69f", "2001/05/30", 9150470699),
        PrepaidCard("Carlos", "Wu", "Shi", "USD", "69000.42f", "2000/11/02", 9424206544123456),
        PrepaidCard("Sherwin", null, "Catli", "PHP", "12345.0f", "1995/04/11", 9215938644987654),
    )

    list.forEach{it.getFormattedDetails()}
}