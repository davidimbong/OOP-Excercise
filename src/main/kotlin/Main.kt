import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

fun main(args: Array<String>) {

    val list = mutableListOf<Account>()
    val symbols = "0123456789/?!:;%"

    //declaring the variables in the loop to allow interoperability with other try catcg
    var accType: String
    var fnameInput: String
    var mnameInput: String
    var lnameInput: String
    var bdayInput: String
    var currInput: String
    var balanceInput: String
    var cardNumInput: String
    var expDateInput: String
    var creditLimitInput: String
    var cvvInput: String
    var accNumInput: String

    var df = SimpleDateFormat("yyyy/MM/dd")
    df.isLenient = false

    val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    main@ while (true) {
        //choose account
        try {
            println("(S) - Savings \n(C) - Credit Card \n(P) - Prepaid Card ")
            print("Choose which Account type to Enroll (input first letter only): ")
            accType = readln().uppercase()

            accType = if (accType == "P")
                "Prepaid Card"
            else if (accType.equals("C"))
                "Credit Card"
            else if (accType.equals("S"))
                "Savings"
            else
                throw IllegalArgumentException("  Please input S C or P only  ")
        } catch (e: IllegalArgumentException) {
            println("\n==============================")
            println("${e.message}")
            println("==============================\n")
            continue
        }

        println("\n\n\n\n==============================   ${accType}   ==============================")
        println("Please input the desired information (please follow the format stated if there are any)")


        //first name
        while (true) {
            try {
                print("First name: ")
                fnameInput = readln()
                if (fnameInput.isBlank())
                    throw InputMismatchException("   First name cannot be blank or empty   ")
                else if (fnameInput.any { it in symbols })
                    throw InputMismatchException("  Name cannot contain numbers or symbols ")
                else
                    break
            } catch (e: InputMismatchException) {
                println("\n=========================================")
                println("${e.message}")
                println("===========================================\n")
                continue
            }
        }


        //middle name
        while (true) {
            try {
                print("Middle name (press enter to skip): ")
                mnameInput = readln()
                if (mnameInput.any { it in symbols }) {
                    throw InputMismatchException("  Name cannot contain numbers or symbols  ")
                }
                else break
            } catch (e: InputMismatchException) {
                println("\n==========================================")
                println("${e.message}")
                println("==========================================\n")
                continue
            }
        }


        //last name
        while (true) {
            try {
                print("Last name: ")
                lnameInput = readln()
                if (lnameInput.isBlank())
                    throw InputMismatchException("   Last name cannot be blank or empty   ")
                else if (lnameInput.isBlank() || lnameInput.any { it in symbols })
                    throw InputMismatchException("  Name cannot contain numbers or symbols  ")
                else break
            } catch (e: InputMismatchException) {
                println("\n==========================================")
                println("${e.message}")
                println("==========================================\n")
                continue
            }
        }


        //birthdate
        while (true) {
            try {
                print("Birthday (YYYY/MM/DD): ")
                bdayInput = readln()
                if (df.parse(bdayInput).after(SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())))
                    throw InputMismatchException("Enter a valid birthday")
                else
                    break
            } catch (e: ParseException) {
                println("\n=======================================")
                println("  Input date in Year/month/day format  ")
                println("          Example 2022/08/30  ")
                println("=======================================\n")
                continue
            } catch (e: InputMismatchException) {
                println("\n======================================")
                println("  Birthday must be before today's date")
                println("======================================\n")
                continue
            }
        }

        //Currency
        while (true) {
            try {
                print("Currency (USD, PHP, JPY): ")
                currInput = readln().uppercase()
                if (currInput.length != 3 || currInput.any { it in symbols }) {
                    throw InputMismatchException("  Select only one of the choices  ")
                } else if (currInput == "USD" || currInput == "JPY" || currInput == "PHP" ) {
                    break
                }
            } catch (e: InputMismatchException) {
                println("\n==================================")
                println("${e.message}")
                println("==================================\n")
                continue
            }
        }

        //balance
        while (true) {
            try {
                print("Balance: ")
                balanceInput = readln()
                if(balanceInput.toBigDecimal() > "0".toBigDecimal())
                break
            } catch (e: NumberFormatException) {
                println("\n======================")
                println("  Only input numbers  ")
                println("======================\n")
                continue
            }
        }

        if (accType == "Prepaid Card" || accType == "Credit Card") {

            //Card Number
            while (true) {
                try {
                    print("Card Number: ")
                    cardNumInput = readln()
                    cardNumInput.toFloat()
                    cardNumInput.filter { !it.isWhitespace() }
                    if (cardNumInput.length != 16)
                        throw InputMismatchException("  Input does not ammount to 16 digits  ")
                    else break
                } catch (e: NumberFormatException) {
                    println("\n======================")
                    println("  Only input numbers  ")
                    println("======================\n")
                    continue
                } catch (e: InputMismatchException) {
                    println("\n=======================================")
                    println(e.message)
                    println("=======================================\n")
                }
            }

            //expiry date
            while (true) {
                try {
                    print("Expiry Date (numbers only): ")
                    expDateInput = readln()
                    expDateInput.toInt()
                    expDateInput.filter { !it.isWhitespace() }

                    if (expDateInput.length != 4)
                        throw InputMismatchException("  Input does not amount to 4 digits  ")
                    //check if within 12 months
                    else if (expDateInput.take(2).toInt() > 12)
                        throw InputMismatchException("     Input is not a proper date    ")
                    else break
                } catch (e: NumberFormatException) {
                    println("\n======================")
                    println("  Only input numbers  ")
                    println("======================\n")
                    continue
                } catch (e: InputMismatchException) {
                    println("\n=======================================")
                    println(e.message)
                    println("=======================================\n")
                }
            }

            //prepaid card
            if (accType == "Prepaid Card") {
                list.add(
                    PrepaidCard(
                        firstname = fnameInput,
                        middlename = mnameInput,
                        lastname = lnameInput,
                        dateOfBirth = bdayInput,
                        currency = currInput,
                        balance = balanceInput,
                        cardNumber = cardNumInput,
                        expiryDate = expDateInput
                    )
                )
            } else if (accType == "Credit Card") {

                //Credit Limit
                while (true) {
                    try {
                        print("Credit Limit: ")
                        creditLimitInput = readln()
                        if(creditLimitInput.toBigDecimal() > "0".toBigDecimal())
                            break
                        else
                            throw InputMismatchException("  Credit limit must be above 0  ")
                    } catch (e: NumberFormatException) {
                        println("\n======================")
                        println("  Only input numbers  ")
                        println("======================\n")
                        continue
                    }
                    catch (e: InputMismatchException){
                        println("\n================================")
                        println(e.message)
                        println("================================\n")
                        continue
                    }
                }

                //cvv
                while (true) {
                    try {
                        print("CVV (back of the card): ")
                        cvvInput = readln()
                        cvvInput.toInt()
                        cvvInput.filter { !it.isWhitespace() }
                        if (cvvInput.length != 3)
                            throw InputMismatchException("  Input does not ammount to 3 digits  ")
                        else break
                    } catch (e: NumberFormatException) {
                        println("\n======================")
                        println("  Only input numbers  ")
                        println("======================\n")
                        continue
                    } catch (e: InputMismatchException) {
                        println("\n=======================================")
                        println(e.message)
                        println("=======================================\n")
                    }
                }

                list.add(
                    CreditCard(
                        firstname = fnameInput,
                        middlename = mnameInput,
                        lastname = lnameInput,
                        dateOfBirth = bdayInput,
                        currency = currInput,
                        balance = balanceInput,
                        cardNumber = cardNumInput,
                        expiryDate = expDateInput,
                        creditLimit = creditLimitInput,
                        availableBalance = "${creditLimitInput.toBigDecimal() - balanceInput.toBigDecimal()}",
                        cvv = cvvInput
                    )
                )
            }
        } else {

            //account number
            while (true) {
                try {
                    print("Account Number(no space): ")
                    accNumInput = readln()
                    accNumInput.toFloat()
                    if (accNumInput.length != 10)
                        throw InputMismatchException("  Input does not ammount to 10 digits  ")
                    else break
                } catch (e: NumberFormatException) {
                    println("\n======================")
                    println("  Only input numbers  ")
                    println("======================\n")
                    continue
                } catch (e: InputMismatchException) {
                    println("\n=======================================")
                    println(e.message)
                    println("=======================================\n")
                }
            }

            list.add(
                Savings(
                    firstname = fnameInput,
                    middlename = mnameInput,
                    lastname = lnameInput,
                    dateOfBirth = bdayInput,
                    currency = currInput,
                    balance = balanceInput,
                    accountNumber = accNumInput
                )
            )
        }
        while (true) {
            try {
                println("(E) - Enroll another account/card \n(P) - Print")
                print("What do you want to do next: ")
                var finalInput = readln().uppercase()

                if (finalInput == "E")
                    break
                else if (finalInput == "P")
                    while (true) {
                        try {
                            if (accType == "Credit Card" || accType.equals("Prepaid Card")) {
                                print("(C) - Print card details \n(A) - Print account details\n")
                                print("What do you want to print: ")

                                var printInput = readln().uppercase()
                                if (printInput == "C") {
                                    mutableListOf<CardDisplayable>().apply {
                                        list.filterIsInstance<CardDisplayable>().forEach {
                                            add(it)
                                        }
                                    }.forEach { it.printCardDetails() }
                                    break@main
                                } else if (printInput == "A") {
                                    list.forEach { it.getFormattedDetails() }
                                    break@main
                                } else
                                    throw IllegalArgumentException("  Please input C or A only  ")
                            } else {
                                list.forEach { it.getFormattedDetails() }
                                break@main
                            }
                        } catch (e: IllegalArgumentException) {
                            println("\n==============================")
                            println("${e.message}")
                            println("==============================\n")
                            continue
                        }
                    }
                else
                    throw IllegalArgumentException("  Please input E or P only  ")
            } catch (e: IllegalArgumentException) {
                println("\n==============================")
                println("${e.message}")
                println("==============================\n")
                continue
            }
            println("\n\n\n")
        }

    }
}

//        Savings(
//            firstname = "Juan",
//            middlename = "De la Cerna",
//            lastname = "De la Cruz",
//            currency = "PHP",
//            balance = "1000.0",
//            dateOfBirth = "2005/01/01",
//            accountNumber = "6001234567"
//        ),
//        PrepaidCard(
//            firstname = "Janine",
//            middlename = "Santos",
//            lastname = "De Leon",
//            currency = "USD",
//            balance = "100000.0",
//            dateOfBirth = "2000/12/25",
//            cardNumber = "1234567899990000",
//            expiryDate = "1222"
//        ),
//        Savings(
//            firstname = "Antonio",
//            middlename = "San Andres",
//            lastname = "Alkalde",
//            currency = "PHP",
//            balance = "12523.62",
//            dateOfBirth = "2004/08/29",
//            accountNumber = "6002444422"
//        ),
//        Savings(
//            firstname = "Floyd Richard",
//            middlename = "Verdejo",
//            lastname = "Tiu",
//            currency = "PHP",
//            balance = "420000.69",
//            dateOfBirth = "2001/05/30",
//            accountNumber = "0915047069"
//        ),
//        PrepaidCard(
//            firstname = "Carlos",
//            middlename = "Wu",
//            lastname = "Shi",
//            currency = "USD",
//            balance = "69000.42",
//            dateOfBirth = "2000/11/02",
//            cardNumber = "9424206544123456",
//            expiryDate = "1223"
//        ),
//        PrepaidCard(
//            firstname = "Sherwin",
//            middlename = null,
//            lastname = "Catli",
//            currency = "PHP",
//            balance = "12345.01",
//            dateOfBirth = "1995/04/11",
//            cardNumber = "9215938644987654",
//            expiryDate = "0924"
//        ),
//        CreditCard(
//            firstname = "David Maxwell Jai",
//            middlename = "Dotillos",
//            lastname = "Imbong",
//            currency = "PHP",
//            balance = "12036.08",
//            dateOfBirth = "2000/05/18",
//            cardNumber = "0921593864401234",
//            cvv = "563",
//            availableBalance = "17963.92",
//            creditLimit = "30000",
//            expiryDate = "0723"
//        ),
//        CreditCard(
//            firstname = "Time",
//            middlename = "Cook",
//            lastname = "Apple",
//            currency = "PHP",
//            balance = "50000.0",
//            dateOfBirth = "2000/02/28",
//            cardNumber = "4126567899991991",
//            cvv = "693",
//            availableBalance = "250000.0",
//            creditLimit = "300000",
//            expiryDate = "0425"
//        )
//    )//.forEach { it.getFormattedDetails() }
//
//    val list2 = mutableListOf<CardDisplayable>().apply {
//        list.filterIsInstance<CardDisplayable>().forEach{
//            add(it)
//        }
//    }.forEach { it.printCardDetails() }