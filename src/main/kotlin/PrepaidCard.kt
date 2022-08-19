import java.text.DecimalFormat
import kotlin.math.roundToInt

class PrepaidCard(firstname: String, middlename: String?, lastname: String, currency: String, balance: String, dateOfBirth: String, cardNumber: Number):
    Account( firstname, middlename, lastname, currency, balance, dateOfBirth) {

    val x = cardNumber.toString()
    val s:StringBuilder = java.lang.StringBuilder("")

    override fun getFormattedDetails() {
        x.mapIndexed{ index, c ->
            if ((index)%4 ==0 && index != 0){
                s.append(" ")
            }
            s.append(c)
        }

        println("$s | $lastname, $firstname ${if(middlename!=null)middlename + " " else "" }| Prepaid Card | $currency ${balance.toFloat().toBigDecimal().setScale(2)} | ${if(isMinor) "MINOR" else "NON-MINOR"}")
    }


}