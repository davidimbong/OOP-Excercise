import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Suppress("LABEL_NAME_CLASH")
fun main(args: Array<String>) {

    val list = mutableListOf<Account>()
    var cardNumInput: String
    var expDateInput: String
    
    main@ while (true) {
        val accType = getAccType()
        val fnameInput = getName("First name")
        val mnameInput = getName("Middle name")
        val lnameInput = getName("Last name")
        val bdayInput = getBday()
        val currInput = getCurr()
        val balanceInput = getMoneyInput("Balance")

        //prepaid card
        when (accType) {
            "Prepaid Card" -> {
                cardNumInput = getAccOrCardNumber(16,"Card")
                expDateInput = getExpiryDate()
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
            }
            "Credit Card" -> {

                cardNumInput = getAccOrCardNumber(16, "Card")
                expDateInput = getExpiryDate()
                val creditLimitInput = getMoneyInput("Credit Limit")
                val cvvInput = getCVV()
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
            "Savings" -> {
                val accNumInput = getAccOrCardNumber(10, "Account")
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
        }

        //ask user for next course of action
        while (true) {
            try {
                println("(E) - Enroll another account/card \n(P) - Print")
                print("What do you want to do next: ")
                val finalInput = readln().uppercase()

                if (finalInput == "E") {
                    println("\n\n\n")
                    break
                } else if (finalInput == "P") {
                    printOptions(list)
                    break@main
                }
                else
                    throw IllegalArgumentException("  Please input E or P only  ")
            } catch (e: IllegalArgumentException) {
                println("\n==============================")
                println("${e.message}")
                println("==============================\n")
                continue
            }
        }
    }
}

fun getAccType(): String {
    var str: String
    while (true) {
        try {
            println("(S) - Savings \n(C) - Credit Card \n(P) - Prepaid Card ")
            print("Choose which Account type to Enroll (input first letter only): ")
            str = readln().uppercase()

            str = when (str) {
                "P" -> "Prepaid Card"
                "C" -> "Credit Card"
                "S" -> "Savings"
                else -> throw IllegalArgumentException("  Please input S C or P only  ")
            }
        } catch (e: IllegalArgumentException) {
            println("\n==============================")
            println("${e.message}")
            println("==============================\n")
            continue
        }
        println("\n\n\n\n==============================   $str   ==============================")
        println("Please input the desired information (please follow the format stated if there are any)")
        return str
    }
}

fun getName(str: String): String {
    var name: String
    while (true) {
        try {
            print("$str: ")
            name = readln()
            if (name.isBlank() && str != "Middle name")
                throw InputMismatchException("   $str cannot be blank or empty   ")
            else if (name.matches(Regex("^[\\p{L}\\s]+$")))
                return name
            else
                throw InputMismatchException("  Name cannot contain numbers or symbols ")
        } catch (e: InputMismatchException) {
            println("\n=========================================")
            println("${e.message}")
            println("===========================================\n")
            continue
        }
    }
}

fun getBday(): String {
    val df = SimpleDateFormat("yyyy/MM/dd")
    df.isLenient = false
    var str: String

    while (true) {
        try {
            print("Birthday(YYYY/MM/DD): ")
            str = readln()
            str = str.filter { !it.isWhitespace() }

            if(!str.matches(Regex("^([0-9]{4})/([0-9]{2})/([0-9]{2})$")))
                throw InputMismatchException("  Input must be in the format (YYYY/MM/DD)\n" +
                                             "           i.e. 2000/12/25")
            else if (df.parse(str).after(SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())))
                throw InputMismatchException("  Birthday must be before today's date")
            else
                return str
        } catch (e: ParseException) {
            println("\n=============================")
            println("  Please enter a valid date  ")
            println("=============================\n")
            continue
        } catch (e: InputMismatchException) {
            println("\n============================================")
            println(e.message)
            println("============================================\n")
            continue
        }
    }
}

fun getExpiryDate(): String {
    val df = SimpleDateFormat("MM/yy")
    df.isLenient = false
    var str: String

    while (true) {
        try {
            print("Expiry Date (MM/YY): ")
            str = readln()
            str = str.filter { !it.isWhitespace() }

            //if(!str.matches(Regex("^(0[1-9]|1[0-2])/([0-9]{2})$")))
            if(!str.matches(Regex("^([0-9]{2})/([0-9]{2})$")))
                throw InputMismatchException("  Input must be in the format (MM/YY)\n" +
                                             "              i.e. 12/25")
             if (!df.parse(str).after(SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())))
                throw InputMismatchException("               Card can not be expired")
            else
                return str
        } catch (e: ParseException) {
            println("\n=============================")
            println("  Please enter a valid date  ")
            println("=============================\n")
            continue
        } catch (e: InputMismatchException) {
            println("\n=======================================")
            println(e.message)
            println("=======================================\n")
            continue
        }
    }
}

fun getCurr(): String {
    var str: String
    while (true) {
        try {
            print("Currency (USD, PHP, JPY): ")
            str = readln().uppercase()
            if (str == "USD" || str == "JPY" || str == "PHP") {
                return str
            } else
                throw InputMismatchException("  Select only one of the choices  ")
        } catch (e: InputMismatchException) {
            println("\n==================================")
            println("${e.message}")
            println("==================================\n")
            continue
        }
    }
}

fun getMoneyInput(output: String): String {
    var str: String
    while (true) {
        try {
            print("$output: ")
            str = readln()
            if (str.toBigDecimal() > "0".toBigDecimal() || output == "Balance")
                return str
            else
                throw InputMismatchException("  $output must be above 0  ")
        } catch (e: NumberFormatException) {
            println("\n======================")
            println("  Only input numbers  ")
            println("======================\n")
            continue
        } catch (e: InputMismatchException) {
            println("\n================================")
            println(e.message)
            println("================================\n")
            continue
        }
    }
}

fun getAccOrCardNumber(maxLength: Int, type:String): String {
    var str: String
    while (true) {
        try {
            print("$type Number: ")
            str = readln()
            str = str.filter { !it.isWhitespace() }
            @Suppress("RegExpSimplifiable")
            if (str.matches(Regex("^[0-9]{$maxLength}+\$")))
                 return str
             else
                 throw InputMismatchException("  Input does not amount to $maxLength digits  ")
        } catch (e: NumberFormatException) {
            println("\n======================")
            println("  Only input numbers  ")
            println("======================\n")
            continue
        } catch (e: InputMismatchException) {
            println("\n=======================================")
            println(e.message)
            println("=======================================\n")
            continue
        }
    }
}

fun getCVV(): String {
    var str: String
    while (true) {
        try {
            print("CVV (back of the card): ")
            str = readln()
            str = str.filter { !it.isWhitespace() }
            if (str.matches(Regex("^[0-9]{3}+\$")))
                return str
            else
                throw InputMismatchException("  Input does not amount to 3 digits  ")
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
}

fun printOptions(list: MutableList<Account>){
    while (true) {
        try {
            print("\n\n\n\n(C) - Print card details \n(A) - Print account details\n")
            print("What do you want to print: ")

            val printInput = readln().uppercase()
            if (printInput == "C") {
                println("\n\n")
                mutableListOf<CardDisplayable>().apply {
                    list.filterIsInstance<CardDisplayable>().forEach {
                        add(it)
                    }
                }.forEach { it.printCardDetails() }
                break
            } else if (printInput == "A") {
                println("\n\n")
                list.forEach { it.getFormattedDetails() }
                break
            } else
                throw IllegalArgumentException("  Please input C or A only  ")
        } catch (e: IllegalArgumentException) {
            println("\n==============================")
            println("${e.message}")
            println("==============================\n")
            continue
        }
    }
}