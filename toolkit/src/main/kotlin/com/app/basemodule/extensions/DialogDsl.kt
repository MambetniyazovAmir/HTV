package com.app.basemodule.extensions

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.app.basemodule.R

fun Activity.alert(init: AlertDialog.Builder.() -> Unit): AlertDialog {
    val builder = AlertDialog.Builder(this, R.style.AppAlertDialogTheme)
    builder.setCancelable(false)
    builder.init()
    return builder.create()
}

/**
alert {
setMessage("""Not implemented yet ¯\_(ツ)_/¯""")
setCancelable(true)
okButton {  }
}.show()
 */

fun Activity.showAlert(title: Int = R.string.error_title, init: AlertDialog.Builder.() -> Unit) = alert {
    setTitle(title)
    init()
}.show()

/**
showAlert {
message(msg.toString())
okButton {  }
setCancelable(true)
}
 */

fun Fragment.alert(init: AlertDialog.Builder.() -> Unit) = activity?.alert(init)

fun android.app.Fragment.alert(init: AlertDialog.Builder.() -> Unit) = activity.alert(init)
fun AlertDialog.Builder.title(title: String) = apply { setTitle(title) }
fun AlertDialog.Builder.title(@StringRes title: Int) = apply { setTitle(title) }
fun AlertDialog.Builder.message(message: String) = apply { setMessage(message) }
fun AlertDialog.Builder.message(@StringRes message: Int) = apply { setMessage(message) }
fun AlertDialog.Builder.okButton(ok: String, action: (() -> Unit)? = null) = apply { setPositiveButton(ok, { _, _ -> action?.invoke() }) }
fun AlertDialog.Builder.okButton(@StringRes ok: Int = android.R.string.ok, action: (() -> Unit)? = null) = apply { setPositiveButton(ok, { _, _ -> action?.invoke() }) }
fun AlertDialog.Builder.cancelButton(cancel: String, action: (() -> Unit)? = null) = apply { setNegativeButton(cancel, { _, _ -> action?.invoke() }) }
fun AlertDialog.Builder.cancelButton(@StringRes cancel: Int = android.R.string.cancel, action: (() -> Unit)? = null) = apply { setNegativeButton(cancel, { _, _ -> action?.invoke() }) }
fun AlertDialog.Builder.icon(@DrawableRes icon: Int) = apply { setIcon(icon) }
fun AlertDialog.Builder.icon(icon: Drawable) = apply { setIcon(icon) }
fun AlertDialog.Builder.iconVector(@DrawableRes icon: Int) = apply { setIcon(this.context.getDrawableFromVector(icon)) }
fun AlertDialog.Builder.contentView(view: View?) = apply { setView(view) }
fun AlertDialog.Builder.contentView(view: Int) = apply { setView(view) }
