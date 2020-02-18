package com.app.basemodule.presentation.base

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.disposables.Disposable

interface IBaseNetworkPresenter {
    /***
     * Enter point of presenter lifecycle
     */
    fun onCreate() {}

    fun onStart() {}
    fun onResume() {}
    fun onPause() {}
    fun onStop() {}
    fun onDisposeView()
    fun <T> createRequestTransformer(): FlowableTransformer<T, T>
    fun <T> createRequestObservableTransformer(requestId: Int?): ObservableTransformer<T, T>
    fun <T> createRequestTransformerSingle(requestId: Int?): SingleTransformer<T, T>
    fun <T> createRequestTransformerNoProgressSingle(requestId: Int?): SingleTransformer<T, T>
    fun onError(throwable: Throwable)
    fun manageDisposable(manageWith: () -> Disposable)

    fun <T> Flowable<T>.attachTransformer() = compose(createRequestTransformer<T>())
    fun <T> Observable<T>.attachTransformer(requestId: Int? = null) = compose(createRequestObservableTransformer<T>(requestId))
    fun <T> Single<T>.attachTransformer(requestId: Int? = null) = compose(createRequestTransformerSingle<T>(requestId))
    fun <T> Single<T>.attachTransformerNoProgress(requestId: Int? = null) = compose(createRequestTransformerNoProgressSingle<T>(requestId))
}
