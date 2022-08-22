import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
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

        val name = "$lastname, $firstname ${if(middlename!=null)middlename + " " else "" }"
        val bal = DecimalFormat("#,##0.00").format(balance.toBigDecimal().setScale(2,RoundingMode.DOWN))

        println("$s | $name| Prepaid Card | $currency $bal | ${if(isMinor) "MINOR" else "NON-MINOR"}")
    }


}