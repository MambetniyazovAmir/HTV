package com.app.basemodule.extensions

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.fragment.app.Fragment

fun AppCompatActivity.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.toggleKeyboard(view: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInputFromWindow(view.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0)
}

fun AppCompatActivity.withDelay(millis: Long, f0: () -> Unit) {
    Handler().postDelayed(f0, millis)
}

fun Fragment.withDelay(millis: Long, f0: () -> Unit) {
    Handler().postDelayed(f0, millis)
}

fun Activity.hideKeyBoard() {
    val view = this.currentFocus
    view ?: return
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.showKeyBoard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Activity.loadAsync(@LayoutRes res: Int, target: ViewGroup? = null, action: View.() -> Unit) =
        AsyncLayoutInflater(this).inflate(res, target) { view, resid, parent ->
            with(parent) {
                this?.addView(view)
                action(view)
            }
        }
