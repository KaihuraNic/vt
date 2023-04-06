package com.app.presentation.util

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import java.text.SimpleDateFormat

class DaysUtil {

    fun getNextFiveDays(): List<String> {
        val dateFormat  = SimpleDateFormat("EEEE")
        var result = mutableListOf<String>()
        for (i in 1..5) {
            val calendar = GregorianCalendar()
            calendar.add(Calendar.DATE, i)
            result.add(dateFormat.format(calendar.time))
        }
        return result
    }
}