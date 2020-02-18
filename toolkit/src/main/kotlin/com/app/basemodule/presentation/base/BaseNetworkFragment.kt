package com.app.basemodule.presentation.base

import android.view.View
import com.app.basemodule.extensions.createRetryNetObs
import com.app.basemodule.extensions.mainThread
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class BaseNetworkFragment : BaseFragment(), BaseNetworkMVPView {

    protected open fun getRootLayout(): View? = view
    override fun getFlowableForRetries(): Flowable<Boolean> = createRetryNetObs(getRootLayout())
    override fun getSingleForReties(): Single<Boolean> = ((activity as BaseNetworkMVPView).getSingleForReties())

    override fun onRequestBegin(requestId: Int?) {
        super.onRequestBegin(requestId)
        showProgress(requestId)
        lockInterfaceOnRequest(requestId)
    }

    override fun onRequestTerminate(requestId: Int?) {
        super.onRequestTerminate(requestId)
        unlockInterfaceAfterRequest(requestId)
        hideProgress(requestId)
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