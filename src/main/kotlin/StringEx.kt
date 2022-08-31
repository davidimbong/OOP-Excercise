import java.math.RoundingMode
import java.text.DecimalFormat

fun String.getFirstCharPerWord(): String {
    val list = this.split(' ')
    val sb = StringBuilder()
    list.forEach {
        sb.append(it[0].uppercaseChar())
    }

    return sb.toString()
}

fun String.uppercaseFirstChar(): String {
    val list = this.split(' ')
    val sb = StringBuilder()
    list.forEach {
        sb.append(" ")
        sb.append(it.replaceFirstChar { it.uppercaseChar() })
    }

    return sb.toString()
}

fun String.separateString(count: Int, separator: Char): String {
    val sb = StringBuilder()
    this.forEachIndexed { index, c ->
        if ((index) % count == 0 && index != 0) {
            sb.append(separator)
        }
        sb.append(c)
    }

    return sb.toString()
}

fun String.convertToMoneyFormat(currency: String): String {
    val formatter = DecimalFormat("#,##0.00")
    val num = this.toBigDecimal().setScale(2, RoundingMode.DOWN)

    val sb = StringBuilder().apply {
        append("${currency.uppercase()} ")
        append(formatter.format(num))
    }.toString()

    return sb
}