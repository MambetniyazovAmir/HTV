package com.app.basemodule.presentation.base

import android.view.ViewGroup
import com.app.basemodule.extensions.createRetryNetObs
import com.app.basemodule.extensions.createRetryNetSingle
import com.app.basemodule.extensions.lockAllChildren
import com.app.basemodule.extensions.mainThread
import com.app.basemodule.extensions.message
import com.app.basemodule.extensions.okButton
import com.app.basemodule.extensions.showAlert
import com.app.basemodule.extensions.unlockAllChildren
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class BaseNetworkActivity : BaseActivity(), BaseNetworkMVPView, LockableView {

    private fun showAlert(msg: String) {
        showAlert {
            message(msg)
            okButton { }
            setCancelable(true)
        }
    }

//  private fun showErrAlert(@StringRes msg: Int = ErrorMessageHelper.UNEXPECTED_ERROR.msg) = showAlert(getString(msg))

    override fun onRequestBegin(requestId: Int?) {
        super.onRequestBegin(requestId)
        lockInterfaceOnRequest(requestId)
        showProgress(requestId)
    }

    override fun onRequestTerminate(requestId: Int?) {
        super.onRequestTerminate(requestId)
        unlockInterfaceAfterRequest()
        hideProgress(requestId)
    }

    override fun lockInterfaceOnRequest(requestId: Int?) {
        super.lockInterfaceOnRequest(requestId)
        getRootLayout()?.lockAllChildren()
    }

    override fun unlockInterfaceAfterRequest(requestId: Int?) {
        super.unlockInterfaceAfterRequest(requestId)
        getRootLayout()?.unlockAllChildren()
    }

    private fun getRootLayout(): ViewGroup? {
        val contentLayout = (findViewById<ViewGroup>(android.R.id.content))
                ?: window.decorView.findViewById(android.R.id.content)
        return contentLayout?.getChildAt(0) as? ViewGroup
    }

    override fun getFlowableForRetries(): Flowable<Boolean> = createRetryNetObs(getRootLayout())
    override fun getSingleForReties(): Single<Boolean> = createRetryNetSingle(getRootLayout())

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
