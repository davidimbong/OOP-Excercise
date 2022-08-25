import java.math.RoundingMode
import java.text.DecimalFormat

fun String.getFirstCharPerWord(): String {
    val list = this.split(' ')
    val sb = StringBuilder()
    list.forEach {
        sb.append(it[0])
    }

    return sb.toString()
}

fun String.separateString( count: Int): String {
    val sb = StringBuilder()
    this.forEachIndexed() { index, c ->
        if ((index) % count == 0) {
            sb.append(" ")
        }
        sb.append(c)
    }

    return sb.toString()
}

fun String.convertToBigDecimal(): String {
    val formatter = DecimalFormat("#,##0.00")
    val num = this.toBigDecimal().setScale(2, RoundingMode.DOWN)

    return formatter.format(num)
}