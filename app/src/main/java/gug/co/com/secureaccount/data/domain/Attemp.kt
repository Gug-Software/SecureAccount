package gug.co.com.secureaccount.data.domain

import java.text.SimpleDateFormat
import java.util.*

class Attemp(
    val id: Int,
    val accountUserName: String,
    val result: Boolean,
    val timeMillis: Long
) {

    val dateFormat: String = formatDate()

    private fun formatDate(): String {

        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.format(Date(timeMillis))
        return date

    }
}