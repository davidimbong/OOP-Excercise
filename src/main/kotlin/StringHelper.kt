import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class StringHelper {
    companion object{
        fun getFirstCharPerWord(input: String): String{
            val list = input.split(' ')
            val sb = StringBuilder()
            list.forEach {
                sb.append(it[0])
            }

            return sb.toString()
        }

        fun separateString(input: String, count: Int): String{
            val sb = StringBuilder()
            input.forEachIndexed() { index, c ->
                if ((index) % count == 0) {
                    sb.append(" ")
                }
                sb.append(c)
            }

            return sb.toString()
        }

        fun convertToBigDecimal(input: String): String{
            val formatter = DecimalFormat("#,##0.00")
            val num = input.toBigDecimal().setScale(2, RoundingMode.DOWN)

            return formatter.format(num)
        }
    }
}