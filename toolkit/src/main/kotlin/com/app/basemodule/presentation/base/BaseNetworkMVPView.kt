package com.app.basemodule.presentation.base

import io.reactivex.Flowable
import io.reactivex.Single

interface BaseNetworkMVPView : BaseMVPView, LockableView {
    fun onSuccess() {}
    fun onRequestBegin(requestId: Int?) {
        showProgress(requestId)
    }

    fun onRequestTerminate(requestId: Int?) {
        hideProgress(requestId)
    }

    fun getFlowableForRetries(): Flowable<Boolean>
    fun getSingleForReties(): Single<Boolean>
    override fun lockInterfaceOnRequest(requestId: Int?) {}
    override fun unlockInterfaceAfterRequest(requestId: Int?) {}
    fun <T> performInBackgroundThread(action: () -> T, onSuccess: (T) -> Unit, onError: (Throwable) -> Unit = Throwable::printStackTrace)
}