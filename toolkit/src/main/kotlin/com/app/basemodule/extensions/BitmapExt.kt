package com.app.basemodule.extensions

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.compresedByteArr(percent: Int): ByteArray {
    val bitmapData: ByteArray
    val bos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, percent, bos)
    bitmapData = bos.toByteArray()
    return bitmapData
}

fun Bitmap.toByteArray() = compresedByteArr(100)