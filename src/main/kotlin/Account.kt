import java.time.LocalDate
import java.time.format.DateTimeFormatter

abstract class Account(
    internal val firstname: String,
    internal val middlename: String?,
    internal val lastname: String,
    internal val currency: String,
    internal val balance: String,
    internal val dateOfBirth: String
) {

    abstract fun getFormattedDetails()

    val birthday: LocalDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy/MM/dd"))

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