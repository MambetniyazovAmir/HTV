package com.app.basemodule.presentation.base

import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.app.basemodule.R
import com.app.basemodule.extensions.hideAnim
import com.app.basemodule.extensions.isTablet
import com.app.basemodule.extensions.logd
import com.app.basemodule.extensions.message
import com.app.basemodule.extensions.okButton
import com.app.basemodule.extensions.screenslog
import com.app.basemodule.extensions.showAlert
import com.app.basemodule.extensions.showAnim
import com.app.basemodule.extensions.toastNotImplemented
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity(), BaseMVPView {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestCorrectOrientation()
        super.onCreate(savedInstanceState)
        screenslog(this.javaClass.canonicalName)

        if (shouldOverridePendingTransition()) {
            enterPendingTransition()
        }
    }

    protected fun requestCorrectOrientation() {
        requestedOrientation = if (isTablet()) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    override fun showErrorMessage(msg: CharSequence) {
        runOnUiThread {
            showAlert {
                setTitle(R.string.error_title)
                message(msg.toString())
                okButton { }
                setCancelable(true)
            }
        }
    }

    override fun showErrorMessage(resId: Int) {
        val str = resources.getString(resId)
        showErrorMessage(str)
    }

    override fun stringFromResources(resId: Int): String = getString(resId)

    open fun shouldOverridePendingTransition() = true
    open fun enterPendingTransition() = overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    open fun exitPendingTransition() = overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    override fun showSimpleDialog(msg: CharSequence) {
        showAlert {
            message(msg.toString())
            okButton { }
            setCancelable(true)
        }
    }

    protected fun hasNavBar(resources: Resources): Boolean {
        val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        return id > 0 && resources.getBoolean(id)
    }

    override fun manageDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun finish() {
        super.finish()

        if (shouldOverridePendingTransition()) {
            exitPendingTransition()
        }
    }

    override fun showProgress(requestId: Int?) {
        findViewById<View>(R.id.progress)?.showAnim()
        logd("progress show")
    }

    override fun hideProgress(requestId: Int?) {
        findViewById<View>(R.id.progress)?.hideAnim()
        logd("progress hide")
    }

    protected fun makeWindowNoLimits() {
        with(window) {
            setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//      setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

    override fun showNotImplementedYetMessage() {
        applicationContext.toastNotImplemented()
    }

    protected fun calculateStatusBarHeight(): Int {
        val rectangle = Rect()
        val window = window
        window.decorView.getWindowVisibleDisplayFrame(rectangle)
        val statusBarHeight = rectangle.top

        return statusBarHeight
    }
}