import java.time.LocalDate
import java.time.format.DateTimeFormatter

abstract class Account(
    val firstname: String,
    val middlename: String?,
    val lastname: String,
    val currency: String,
    val balance: String,
    val dateOfBirth: String
) {

    abstract fun getFormattedDetails()

    var birthday: LocalDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy/MM/dd"))

    val isMinor: String
        get() {
            if (java.time.Period.between(birthday, LocalDate.now()).years < 18) {
                return "MINOR"
            }
            return "NON-MINOR"
        }

    val fullname = StringBuilder().apply {
        append(lastname)
        append(", ")
        append(firstname)

        if (!middlename.isNullOrBlank()) {
            append(" ")
            append(middlename.getFirstCharPerWord())
        }
    }.toString()
}