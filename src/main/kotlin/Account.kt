import java.time.LocalDate
import java.time.format.DateTimeFormatter

abstract class Account(
    protected val firstname: String,
    protected val middlename: String?,
    protected val lastname: String,
    protected val currency: String,
    protected val balance: String,
    protected val dateOfBirth: String
) {

    abstract fun getFormattedDetails()

    protected val birthday: LocalDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy/MM/dd"))

    protected val isMinor: String
        get() {
            if (java.time.Period.between(birthday, LocalDate.now()).years < 18) {
                return "MINOR"
            }
            return "NON-MINOR"
        }

    protected val fullname = StringBuilder().apply {
        append(lastname)
        append(", ")
        append(firstname)

        if (!middlename.isNullOrBlank()) {
            append(" ")
            append(middlename.getFirstCharPerWord())
        }
    }.toString()
}