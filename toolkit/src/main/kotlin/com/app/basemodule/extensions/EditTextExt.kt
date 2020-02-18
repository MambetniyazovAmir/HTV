package com.app.basemodule.extensions

import android.graphics.PorterDuff
import android.widget.EditText

fun EditText.setBottomLineColor(color: Int) {
    background.mutate().setColorFilter(this.context.getColorCompat(color), PorterDuff.Mode.SRC_ATOP)
}
/**
edtStatusName.setBottomLineColor(R.color.cloudy_blue)
 */