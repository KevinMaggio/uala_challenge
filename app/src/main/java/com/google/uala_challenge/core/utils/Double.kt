package com.google.uala_challenge.core.utils

import java.util.Locale

fun Double?.toTwoDecimalString(): String {
    if (this == null) return "0.00"
    return String.format(Locale.US, "%.2f", this)
}