import java.text.NumberFormat
import java.util.*

class Savings(firstname: String, middlename: String?, lastname: String, currency: String, balance: String, dateOfBirth: String, accountNumber: Number):
    Account( firstname, middlename, lastname, currency, balance, dateOfBirth) {

    val x = accountNumber.toString()
    val s:StringBuilder = java.lang.StringBuilder("")

    override fun getFormattedDetails() {
        x.mapIndexed{ index, c ->
            if ((index)%4 ==0 && index != 0){
                s.append(" ")
            }
            s.append(c)
        }

        println("$s | $lastname, $firstname ${if(middlename!=null)middlename + " " else "" } | Savings Account | $currency ${balance.toFloat().toBigDecimal().setScale(2)} | ${if(isMinor) "MINOR" else "NON-MINOR"}")
    }

}