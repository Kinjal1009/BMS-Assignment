package com.example.bmsAssignment.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun getDateInSpecificFormat(date: String): String? {
    val oldDate = replaceDaySuffix(date)
    val sdf = SimpleDateFormat("d MMMM yyyy")
    val newDate = sdf.parse(oldDate)
    return newDate?.toString()
}


private fun replaceDaySuffix(date: String): String {
    var replaceString = ""
    if (date.contains("st")) {
        replaceString = date.replace("st", "")
    } else if (date.contains("nd")) {
        replaceString = date.replace("nd", "")
    } else if (date.contains("rd")) {
        replaceString = date.replace("rd", "")
    } else if (date.contains("th")) {
        replaceString = date.replace("th", "")
    }
    return replaceString.replace(",", "")
}
