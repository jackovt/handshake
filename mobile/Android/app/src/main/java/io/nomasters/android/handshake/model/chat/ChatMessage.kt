package io.nomasters.android.handshake.model.chat

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
class ChatMessage(
    val messageText: String,
    val fromId: String,
    val timestampSent: Long,
    val timestampUpdated: Long,
    val duress: Boolean = false
) {

    fun getSentTime(): String {
        val date = Date(timestampSent)
        val formatter = createFormatterForSent(date)
        return formatter.format(date)
    }

    private fun createFormatterForSent(date: Date): SimpleDateFormat {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val now = calendar.time
        calendar.time = date
        val sentYear = calendar.get(Calendar.YEAR)
        val sentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val pattern = if (year == sentYear && dayOfYear == sentDayOfYear) "HH:mm" else "MMM dd"
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter
    }
}