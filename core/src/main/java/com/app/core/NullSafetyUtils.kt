package com.app.core

fun Float?.zeroIFNull(): Float = this ?: 0f

fun Long?.zeroIfNull(): Long = this ?: 0

fun Double?.zeroIfNull(): Double = this ?: 0.0

fun String?.emptyIfNull(): String = this ?: ""
