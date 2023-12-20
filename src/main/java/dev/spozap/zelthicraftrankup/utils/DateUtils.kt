package dev.spozap.zelthicraftrankup.utils

import java.util.*
import kotlin.math.abs


private const val MAX_YEARS = 100000

class DateUtils {

    companion object {

        fun formatDate(date: Long) : String {
            val calendar = GregorianCalendar()
            calendar.timeInMillis = date

            val to = GregorianCalendar()
            return formatDateDiff(calendar, to)
        }

        fun formatDateDiff(fromDate: Calendar, toDate: Calendar): String {
            var future = false
            if (toDate == fromDate) {
                return "now"
            }
            if (toDate.after(fromDate)) {
                future = true
            }
            // Temporary 50ms time buffer added to avoid display truncation due to code execution delays
            toDate.add(Calendar.MILLISECOND, if (future) 50 else -50)
            val sb = StringBuilder()
            val types = intArrayOf(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND)
            val names = arrayOf("año", "años", "mes", "meses", "dia", "dias", "hora", "horas", "minuto", "minutos", "segundo", "segundos")
            var accuracy = 0
            for (i in types.indices) {
                if (accuracy > 2) {
                    break
                }
                val diff = dateDiff(types[i], fromDate, toDate, future)
                if (diff > 0) {
                    accuracy++
                    sb.append(" ").append(diff).append(" ").append(names[i * 2 + (if (diff > 1) 1 else 0)])
                }
            }
            // Preserve correctness in the original date object by removing the extra buffer time
            toDate.add(Calendar.MILLISECOND, if (future) -50 else 50)
            return if (sb.length == 0) {
                "now"
            } else sb.toString().trim { it <= ' ' }
        }

        fun dateDiff(type: Int, fromDate: Calendar, toDate: Calendar, future: Boolean): Int {
            val year = Calendar.YEAR
            val fromYear = fromDate[year]
            val toYear = toDate[year]
            if (abs((fromYear - toYear).toDouble()) > MAX_YEARS) {
                toDate[year] = fromYear +
                        if (future) MAX_YEARS else - MAX_YEARS
            }
            var diff = 0
            var savedDate = fromDate.getTimeInMillis()
            while (future && !fromDate.after(toDate) || !future && !fromDate.before(toDate)) {
                savedDate = fromDate.getTimeInMillis()
                fromDate.add(type, if (future) 1 else -1)
                diff++
            }
            diff--
            fromDate.setTimeInMillis(savedDate)
            return diff
        }

    }

}