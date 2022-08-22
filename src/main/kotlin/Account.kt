import java.time.LocalDate
import java.time.format.DateTimeFormatter

abstract class Account( val firstname: String, val middlename: String?,
                        val lastname: String, val currency: String, val balance: String, val dateOfBirth: String){

    abstract fun getFormattedDetails()

    var birthday: LocalDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy/MM/dd"))

    val isMinor: Boolean
        get() {
            if (java.time.Period.between(birthday, LocalDate.now()).years <= 18) {
                return true
            }
            return false
        }


}