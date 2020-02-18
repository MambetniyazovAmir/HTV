package com.app.basemodule.presentation.base

import androidx.annotation.StringRes
import io.reactivex.disposables.Disposable

interface BaseMVPView {
    fun showProgress(requestId: Int? = null)
    fun hideProgress(requestId: Int? = null)
    fun manageDisposable(disposable: Disposable)
    fun showErrorMessage(msg: CharSequence)
    fun stringFromResources(@StringRes resId: Int): String
    fun showErrorMessage(@StringRes resId: Int)
    fun showSimpleDialog(msg: CharSequence)
    fun showNotImplementedYetMessage()
}