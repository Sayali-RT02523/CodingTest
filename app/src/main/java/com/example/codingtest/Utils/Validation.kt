package com.example.codingtest.Utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Validation {

    @SuppressLint("SimpleDateFormat")
    fun timeChecker(dateString1: String, dateString2: String): Boolean {


        val currentTime1 = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val df1 = SimpleDateFormat("HH:mm")
        var date1: Date? = null
        var date2: Date? = null
        var currentTime: Date? = null

        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val currentDay: String = sdf.format(d)
        try {
            date1 = df1.parse(dateString1)
            date2 = df1.parse(dateString2)
            currentTime = df1.parse(currentTime1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (currentTime != null) {
            if (currentTime.before(date1) || currentTime.after(date2)) {
                return false
            } else {
                return !currentDay.equals("SATURDAY") || !currentDay.equals("SUNDAY")
            }
        }
        return false
    }
}