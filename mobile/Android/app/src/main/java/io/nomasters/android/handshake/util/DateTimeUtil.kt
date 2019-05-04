package io.nomasters.android.handshake.util

import android.content.Context
import io.nomasters.android.handshake.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/29/19
 */
class DateTimeUtil {
    companion object {
        val FORMAT_HOURS_MINUTES = "HH:mm"
        val FORMAT_MONTH_DAY = "MMM dd"

        fun formatTimeByClosestTimeOrDate(timestamp: Long): String {
            val date = Date(timestamp)
            val formatter = createFormatterForClosestTimeOrDate(date)
            return formatter.format(date)
        }

        fun formatTimeDifference(context: Context, timeEnd: Long, timeStart: Long): String {
            val timeDifference = timeEnd - timeStart
            return getExpiresString(context, timeDifference)
        }

        fun formatTimeAgo(context: Context, timeEnd: Long, timeStart: Long): String {
            val timeDifference = timeEnd - timeStart
            return getAgoString(context, timeDifference)
        }

        private fun getExpiresString(context: Context, timeDifference: Long): String {
            val days = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS).toInt()
            val hours = TimeUnit.HOURS.convert(timeDifference, TimeUnit.MILLISECONDS).toInt()
            val minutes = TimeUnit.MINUTES.convert(timeDifference, TimeUnit.MILLISECONDS).toInt()
            val seconds = TimeUnit.SECONDS.convert(timeDifference, TimeUnit.MILLISECONDS).toInt()

            if (days > 0) {
                return context.resources.getQuantityString(R.plurals.time_days, days, days)
            }
            if (hours > 0) {
                return context.resources.getQuantityString(R.plurals.time_hours, hours, hours)
            }
            if (minutes > 0) {
                return context.resources.getQuantityString(R.plurals.time_minutes, minutes, minutes)
            }
            if (seconds > 0) {
                return context.resources.getQuantityString(R.plurals.time_seconds, seconds, seconds)
            }
            return ""
        }

        private fun getAgoString(context: Context, timeDifference: Long): String {
            val days = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS).toInt()
            val hours = TimeUnit.HOURS.convert(timeDifference, TimeUnit.MILLISECONDS).toInt()
            val minutes = TimeUnit.MINUTES.convert(timeDifference, TimeUnit.MILLISECONDS).toInt()
            val seconds = TimeUnit.SECONDS.convert(timeDifference, TimeUnit.MILLISECONDS).toInt()

            if (days > 0) {
                return context.resources.getQuantityString(R.plurals.time_days_ago, days, days)
            }
            if (hours > 0) {
                return context.resources.getQuantityString(R.plurals.time_hours_ago, hours, hours)
            }
            if (minutes > 0) {
                return context.resources.getQuantityString(R.plurals.time_minutes_ago, minutes, minutes)
            }
            if (seconds > 0) {
                return context.resources.getQuantityString(R.plurals.time_seconds_ago, seconds, seconds)
            }
            return ""
        }

        private fun createFormatterForClosestTimeOrDate(date: Date): SimpleDateFormat {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
            calendar.time = date
            val sentYear = calendar.get(Calendar.YEAR)
            val sentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
            val pattern = if (year == sentYear && dayOfYear == sentDayOfYear) FORMAT_HOURS_MINUTES else FORMAT_MONTH_DAY
            val formatter = SimpleDateFormat(pattern, Locale.getDefault())
            return formatter
        }
    }
}