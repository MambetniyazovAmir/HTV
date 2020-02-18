package com.app.basemodule.extensions

import android.graphics.Color

fun Int.adjustAlpha(factor: Float): Int {
    Color.parseColor(this.toString())

    val alpha = Math.round(Color.alpha(this) * factor)
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)
    return Color.argb(alpha, red, green, blue)
}
