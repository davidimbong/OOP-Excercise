import java.lang.StringBuilder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

enum class AccountType(val longDesc: String, val maxLength: Int) {
    SA("Savings Account", 10),
    CC("Credit Card", 16),
    PC("Prepaid Card", 16)
//    SAVINGS,
//    CREDITCARD,
//    PREPAIDCARD
}

enum class Currency(val shortCode: String, val longDesc: String) {
    PHP("PHP", "Philippine Peso"),
    USD("USD", "United States Dollar"),
    JPY("JPY", "Japanese Yen")
}

enum class Name(val stringVal: String,val isMandatory: Boolean){
    FIRSTNAME("First name",true),
    MIDDLENAME("Middle name",false),
    LASTNAME("Last name",true),
}

enum class MoneyInput(val StringVal:String, val negativeAllowed: Boolean, val zeroAllowed: Boolean){
    BALANCE("Balance",
        true,
        true),

    CREDITLIMIT("Credit Limit",
        false,
        false),

    AVAILABLEBALANCE("Available Balance",
        false,
        true)
}

fun main(args: Array<String>) {

    val list = mutableListOf<Account>()
    var cardNumInput: String
    var expDateInput: String

    mainLoop@ while (true) {
        val accType = getAccType()
        val fnameInput = getName(Name.FIRSTNAME)
        val mnameInput = getName(Name.MIDDLENAME)
        val lnameInput = getName(Name.LASTNAME)
        val bdayInput = getBday()
        val currInput = getCurr()
        val balanceInput = getMoneyInput(MoneyInput.BALANCE)

        //prepaid card
        when (accType) {
            AccountType.PC -> {
                cardNumInput = getAccOrCardNumber(AccountType.PC)
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

            AccountType.CC -> {

                cardNumInput = getAccOrCardNumber(AccountType.CC)
                expDateInput = getExpiryDate()
                val creditLimitInput = getMoneyInput(MoneyInput.CREDITLIMIT)
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

            AccountType.SA -> {
                val accNumInput = getAccOrCardNumber(AccountType.SA)
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
                println("\n\n\n(E) - Enroll another account/card \n(P) - Print\n(X) - Exit")
                print("What do you want to do next: ")
                val finalInput = readln().uppercase()

                if (finalInput == "E") {
                    println("\n\n\n")
                    break
                } else if (finalInput == "P") {
                    printOptions(list)
                } else if(finalInput == "X"){
                    break@mainLoop
                } else
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

fun getAccType(): Enum<AccountType> {
    var str: AccountType
    var input: String
    while (true) {
        try {
            println("(SA) - Savings Account \n(CC) - Credit Card \n(PC) - Prepaid Card ")
            print("Choose which Account type to Enroll (input first letter only): ")
            input = readln().uppercase()

            str = AccountType.valueOf(input)
        } catch (e: IllegalArgumentException) {
            println("\n=================================")
            println("  Please input SA CC or PC only  ")
            println("=================================\n")
            continue
        }
        println("\n\n\n\n==============================   ${str.longDesc}   ==============================")
        println("Please input the desired information (please follow the format stated if there are any)")
        return str
    }
}

fun getName(str: Name): String {
    var name: String
    while (true) {
        try {
            print("${str.stringVal}: ")
            name = readln()
            if (name.isBlank() && str.isMandatory)
                throw InputMismatchException("   ${str.stringVal} cannot be blank or empty   ")
            else if (name.any { it in "0123456789/?!:;%" } )
                throw InputMismatchException("  Name cannot contain numbers or symbols ")
            else
                return name.lowercase()
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

            if (!str.matches(Regex("^(\\d{4})/(\\d{2})/(\\d{2})$")))
                throw InputMismatchException(
                    "  Input must be in the format (YYYY/MM/DD)\n" +
                            "           i.e. 2000/12/25"
                )
            else if (df.parse(str).after(SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())))
                throw InputMismatchException("    Birthday must be before today's date")
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
            if (!str.matches(Regex("^(\\d{2})/(\\d{2})$")))
                throw InputMismatchException(
                    "  Input must be in the format (MM/YY)\n" +
                            "              i.e. 12/25"
                )
            if (!df.parse(str).after(SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())))
                throw InputMismatchException("      Card can not be expired")
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

fun getCurr(): Currency {
    var str: String
    while (true) {
        try {
            print("Currency (USD, PHP, JPY): ")
            str = readln().uppercase()
            return Currency.valueOf(str)
        } catch (e: IllegalArgumentException) {
            println("\n==================================")
            println("  Select only one of the choices  ")
            println("==================================\n")
            continue
        }
    }
}

fun getMoneyInput(output: MoneyInput): String {
    var str: String
    while (true) {
        try {
            print("${output.StringVal}: ")
            str = readln()
            if ((str.toBigDecimal() > "0".toBigDecimal() || output.zeroAllowed) || output.negativeAllowed)
                return str
            else
                throw InputMismatchException("  ${output.StringVal} must be above 0  ")
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

fun getAccOrCardNumber(type: AccountType): String {
    var str: String
    while (true) {
        try {
            print("${type.longDesc} Number: ")
            str = readln()
            str = str.filter { !it.isWhitespace() }
            val patternSB = StringBuilder().apply {
                append("^\\d{")
                append(type.maxLength)
                append("}+\$")
            }
            if (str.matches(Regex("$patternSB")))
                return str
            else
                throw InputMismatchException("  Input does not amount to ${type.maxLength} digits  ")
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
            if (str.matches(Regex("^\\d{3}+\$")))
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

fun printOptions(list: MutableList<Account>) {
    println("\n\n\n")
    while (true) {
        try {
            print("\n(C) - Print card details \n(A) - Print account details\n(X) - Exit \n")
            print("What do you want to print: ")

            val printInput = readln().uppercase().filter { !it.isWhitespace() }
            if (printInput == "C") {
                println("\n\n")
                val list2 = mutableListOf<CardDisplayable>().apply {
                    list.filterIsInstance<CardDisplayable>().forEach {
                        add(it)
                    }
                }
                if (list2.isEmpty()){
                    throw IllegalArgumentException("  No cards have been enrolled")
                }
                else {
                    println("======= Card Details =======")
                    list2.forEach { it.printCardDetails() }
                    println("============================")
                }
            } else if (printInput == "A") {
                println("\n\n")
                println("======================================== Account Details ========================================")
                list.forEach { it.getFormattedDetails() }
                println("======================================== Account Details ========================================")
            } else if(printInput == "X"){
                break
            } else
                throw IllegalArgumentException("   Please input C or A only  ")
        } catch (e: IllegalArgumentException) {
            println("\n===============================")
            println("${e.message}")
            println("===============================\n")
            continue
        }
    }
}