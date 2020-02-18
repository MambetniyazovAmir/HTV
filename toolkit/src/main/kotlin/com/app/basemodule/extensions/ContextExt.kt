package com.app.basemodule.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.transition.Transition
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.app.basemodule.R
import java.lang.reflect.InvocationTargetException
import java.util.Date
import java.util.Calendar

fun Context.getLocationManater() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

fun Context.dpToPx(dp: Float): Float {
    val resources = resources
    val metrics = resources.displayMetrics
    val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    return px
}

fun Context.pxToDp(px: Float): Float {
    val resources = resources
    val metrics = resources.displayMetrics
    val dp = px * DisplayMetrics.DENSITY_DEFAULT / (metrics.densityDpi.toFloat())
    return dp
}

fun Context.getColorCompat(@ColorRes colorId: Int): Int = ContextCompat.getColor(this, colorId)
// fun Fragment.getColorCompat(@ColorRes colorId: Int) = activity.getColorCompat(colorId)
/**
tvUserInitials.setTextColor(context.getColorCompat(textColorRes))
 * */
fun Fragment.getColorCompat(@ColorRes colorId: Int) = ContextCompat.getColor(this.activity!!, colorId)

fun Context.getColorFromAttrs(@AttrRes resId: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(resId, typedValue, true)
    return typedValue.data
}

fun Context.toastSH(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toastLN(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.getNavigationBarPosition(): Point {
    val appUsableSize = getAppUsableScreenSize()
    val realScreenSize = getRealScreenSize()

    // navigation bar on the right
    if (appUsableSize.x < realScreenSize.x) {
        return Point(realScreenSize.x - appUsableSize.x, appUsableSize.y)
    }

    // navigation bar at the bottom
    if (appUsableSize.y < realScreenSize.y) {
        return Point(appUsableSize.x, realScreenSize.y - appUsableSize.y)
    }

    // navigation bar is not present
    return Point()
}

fun Context.getNavigationBarHeight(): Int {
    val appUsableSize = getAppUsableScreenSize()
    val realScreenSize = getRealScreenSize()

    // navigation bar at the bottom
    if (appUsableSize.y < realScreenSize.y) {
        return (realScreenSize.y - appUsableSize.y)
    }

    // navigation bar on the right
    if (appUsableSize.x < realScreenSize.x) {
        return (realScreenSize.x - appUsableSize.x)
    }

    return 0
}

fun Context.getAppUsableScreenSize(): Point {
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}

fun Context.toastNotImplemented() {
    Toast.makeText(this, """ not implemented yet ¯\_(ツ)_/¯ """, Toast.LENGTH_SHORT).show()
}

fun Context.getRealScreenSize(): Point {
    val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()

    if (Build.VERSION.SDK_INT >= 17) {
        display.getRealSize(size)
    } else if (Build.VERSION.SDK_INT >= 14) {
        try {
            size.x = (Display::class.java.getMethod("getRawWidth").invoke(display) as Int)
            size.y = (Display::class.java.getMethod("getRawHeight").invoke(display) as Int)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        }
    }

    return size
}

fun Context.inflate(@LayoutRes resId: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View = LayoutInflater.from(this).inflate(resId, root, attachToRoot)
/**
context.inflate(R.layout.toolbar_with_status, this, true)
 */

fun Context.getCompatColor(@ColorInt resId: Int) = ContextCompat.getColor(this, resId)

fun Context.getCompatDrawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)

fun Context.isOnline(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}

fun Context.dpToPx(dps: Int): Int {
    return Math.round(this.resources.displayMetrics.density * dps).toInt()
}

fun Context.getDrawableFromVector(res: Int): VectorDrawableCompat? {
    return VectorDrawableCompat.create(this.resources, res, this.theme)
}

fun Context.getBitmapFromVectorDrawableRes(res: Int): Bitmap? {
    val vectorDrawable = getDrawableFromVector(res)
    return vectorDrawable?.let {
        val bitmap = Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        it.setBounds(0, 0, canvas.width, canvas.height)
        it.draw(canvas)
        bitmap
    }
}

@SuppressLint("NewApi")
fun initSharedViewTransitionListener(window: Window, onTransitionFinish: () -> Unit) {
    val sharedElementEnterTransition = window.sharedElementEnterTransition
    sharedElementEnterTransition.addListener(object : Transition.TransitionListener {
        override fun onTransitionEnd(p0: Transition?) {
            onTransitionFinish()
            sharedElementEnterTransition.removeListener(this)
        }

        override fun onTransitionResume(p0: Transition?) {
        }

        override fun onTransitionPause(p0: Transition?) {
        }

        override fun onTransitionCancel(p0: Transition?) {
        }

        override fun onTransitionStart(p0: Transition?) {
        }
    })
}

fun Activity.finishCompatAfterTransition() = ActivityCompat.finishAfterTransition(this)

fun Activity.showErrorAlert(throwable: Throwable) {
    TODO()
//    throwable.printStackTrace()
//    val errorItem = getErrorKind(throwable)
//    Logger.d(errorItem.kind.stringRes)
//    alert {
//        setTitle(R.string.alert_error)
//        message(errorItem.errorBody?.msg ?: getString(errorItem.kind.stringRes))
//        okButton()
//    }.show()
}

fun Activity.showNotImplementedDialog() {
    alert {
        setMessage("""Not implemented yet ¯\_(ツ)_/¯""")
        setCancelable(true)
        okButton { }
    }.show()
}

fun Fragment.showNotImplementedDialog() {
    activity?.showNotImplementedDialog()
}

fun Fragment.showErrorAlert(throwable: Throwable) {
    activity?.showErrorAlert(throwable)
}

fun Context.makeCall(number: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null))
    if (intent.resolveActivity(this.packageManager) != null) this.startActivity(intent)
    else Toast.makeText(this, this.getString(R.string.info_err_msg_no_call), Toast.LENGTH_LONG).show()
}

fun Context.showDatePicker(callback: (Date) -> Unit, @StyleRes theme: Int) {
    val currentDate = Calendar.getInstance()
    val resultDate = Calendar.getInstance()
    DatePickerDialog(this, theme, DatePickerDialog.OnDateSetListener { _, year, month, day ->
        resultDate.set(year, month, day)
        callback(resultDate.toDate())
    }, currentDate.year(), currentDate.month(), currentDate.date()).show()
}

fun Context.browse(link: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(link)
    startActivity(intent)
}

fun Context.withStyleAttributes(attrs: AttributeSet?, filter: IntArray, block: TypedArray.() -> Unit) {
    with(obtainStyledAttributes(attrs, filter)) {
        block()
        recycle()
    }
}

fun Context.makeCallEvent(number: String?) {
    TODO()
//    number ?: return
//    val numbers = number.split("/").filter(String::isNotBlank)
//    if (numbers.size == 1)
//        makeCall(number)
//    else {
//        val builderSingle = AlertDialog.Builder(this)
//        builderSingle.setIcon(R.drawable.ic_call)
//        builderSingle.setTitle(R.string.mess_choose_number)
//        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item)
//        numbers.forEach { arrayAdapter.add(it) }
//        builderSingle.setAdapter(arrayAdapter)
//        { _, which -> makeCall(numbers[which]) }
//        builderSingle.show()
//    }
}

fun Context.isApplicationOnPause(): Boolean {
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val tasks = activityManager.getRunningTasks(1)
    if (!tasks.isEmpty()) {
        val topActivity = tasks[0].topActivity
        return topActivity.packageName != packageName
    }
    return false
}

fun Activity.enterAnimation() = overridePendingTransition(R.anim.right_in, R.anim.left_out)

fun Activity.getStatusBarHeight(): Int {
    val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resId > 0) return resources.getDimensionPixelSize(resId)
    else return (Math.ceil(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 25.0 else 24.0) * resources
            .displayMetrics
            .density
            ).toInt()
}

fun Context.pixelsToDp(pixels: Int): Float {
    val metrics = resources.displayMetrics
    return pixels.toFloat() / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}
