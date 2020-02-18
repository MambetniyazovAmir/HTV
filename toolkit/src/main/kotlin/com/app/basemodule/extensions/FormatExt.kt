package com.app.basemodule.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

const val DEFAULT_DECIMAL_FORMAT = "#,###.##"

fun Float.formatDecimals(format: String = DEFAULT_DECIMAL_FORMAT): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply { groupingSeparator = ' ' }
    val decimalFormat = DecimalFormat(format, symbols)
    return decimalFormat.format(this)
}

fun Double.formatDecimals(format: String = DEFAULT_DECIMAL_FORMAT): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply { groupingSeparator = ' ' }
    val decimalFormat = DecimalFormat(format, symbols)
    return decimalFormat.format(this)
}

/**
it.value.formatDecimals()
 */
fun BigDecimal.formatDecimals(scaleDigit: Int = 2, roundingMode: Int = BigDecimal.ROUND_DOWN): String {
    val bd = setScale(scaleDigit, roundingMode)
    val df = DecimalFormat()
    df.maximumFractionDigits = 2
    df.minimumFractionDigits = 0
    df.isGroupingUsed = false
    return df.format(bd)
}