package com.app.basemodule.presentation.base

import android.view.ViewGroup
import android.widget.ProgressBar
import com.app.basemodule.R
import com.app.basemodule.extensions.createRetryNetObs
import com.app.basemodule.extensions.createRetryNetSingle
import com.app.basemodule.extensions.hideAnim
import com.app.basemodule.extensions.mainThread
import com.app.basemodule.extensions.message
import com.app.basemodule.extensions.okButton
import com.app.basemodule.extensions.plus
import com.app.basemodule.extensions.showAlert
import com.app.basemodule.extensions.showAnim
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseNetworkDialog : BaseFragmentDialog(), BaseNetworkMVPView {
    protected val disposables by lazy { CompositeDisposable() }

    abstract fun getRootView(): ViewGroup
    override fun getFlowableForRetries(): Flowable<Boolean> = createRetryNetObs(getRootView())
    override fun getSingleForReties(): Single<Boolean> = createRetryNetSingle(getRootView())

    override fun showProgress(requestId: Int?) {
        getRootView().findViewById<ProgressBar>(R.id.progress).showAnim()
    }

    override fun hideProgress(requestId: Int?) {
        getRootView().findViewById<ProgressBar>(R.id.progress).hideAnim()
    }

    override fun manageDisposable(disposable: Disposable) = disposables + disposable

    override fun showErrorMessage(msg: CharSequence) {
        (activity as? BaseMVPView)?.showErrorMessage(msg)
    }

    override fun stringFromResources(resId: Int): String = resources.getString(resId)

    override fun showErrorMessage(resId: Int) {
        val str = resources.getString(resId)
        showErrorMessage(str)
    }

    override fun showSimpleDialog(msg: CharSequence) {
        activity?.showAlert {
            setTitle(R.string.error_title)
            message(msg.toString())
            okButton { }
            setCancelable(true)
        }
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun <T> performInBackgroundThread(action: () -> T, onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        val disposable = Single.fromCallable(action)
                .subscribeOn(Schedulers.computation())
                .mainThread()
                .doOnSubscribe { onRequestBegin(-1) }
                .doFinally { onRequestTerminate(-1) }
                .subscribe(onSuccess, onError)
        disposables.add(disposable)
    }
}
