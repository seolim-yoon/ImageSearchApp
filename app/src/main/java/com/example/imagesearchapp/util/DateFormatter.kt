package com.example.imagesearchapp.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun dateFormatter(date: String): String =
    try {
        LocalDateTime
            .parse(date, DateTimeFormatter.ISO_DATE_TIME)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    } catch (e: Exception) {
        ""
    }


