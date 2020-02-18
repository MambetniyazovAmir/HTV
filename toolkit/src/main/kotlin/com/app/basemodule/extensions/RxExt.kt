package com.app.basemodule.extensions

import android.os.Looper
import android.view.View
import androidx.lifecycle.LiveDataReactiveStreams
import com.app.basemodule.R
import com.app.basemodule.utils.NetworkRetryUtil.exponentialBackoffForExceptions
import com.app.basemodule.utils.RxModalScreen
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeUnit

infix operator fun CompositeDisposable.plus(disp: Disposable) {
    this.add(disp)
}

fun <T> Observable<T>.mainThread() = compose { observeOn(AndroidSchedulers.mainThread()) }
/**
manageDisposable {
attachmentsSubject
.throttleFirst(ATTACHMENT_SUBJECT_DEBOUNCE, MILLISECONDS)
.mainThread()
.subscribe(view::openAttachment, Throwable::printStackTrace)
}
 */
fun <T> Observable<T>.io() = compose { observeOn(Schedulers.io()) }

/**
getItemInStatus(statusId, sorting.defaultSortOrder, newOffset, PaginationInStatusResponse.ITEMS_TO_LOAD_AT_TIME)
.toObservable()
.mainThread()
.doOnSubscribe {
if (shouldLoadMore) view.showPaginationLoading(statusId)
}
.doOnNext { cacheResponse(it) }
.io()
.map(getPaginationTransformation())
 */
fun <T> Observable<T>.computation() = compose { observeOn(Schedulers.computation()) }

fun Completable.mainThread() = compose { observeOn(AndroidSchedulers.mainThread()) }
fun Completable.io() = compose { observeOn(Schedulers.io()) }
fun Completable.computation() = compose { observeOn(Schedulers.computation()) }

fun <T> Single<T>.retryUntilNetworkSuccess() = compose {
    retryWhen(exponentialBackoffForExceptions(2, 1000,
            TimeUnit.SECONDS, IOException::class.java))
}

fun <T> Flowable<T>.retryUntilNetworkSuccess() = compose {
    retryWhen(exponentialBackoffForExceptions(2, 1000,
            TimeUnit.SECONDS, IOException::class.java))
}

fun <T> Single<T>.mainThread() = compose { observeOn(AndroidSchedulers.mainThread()) }
fun <T> Single<T>.io() = compose { observeOn(Schedulers.io()) }
fun <T> Single<T>.computation() = compose { observeOn(Schedulers.computation()) }

fun <T> Flowable<T>.mainThread() = compose { observeOn(AndroidSchedulers.mainThread()) }
fun <T> Flowable<T>.io() = compose { observeOn(Schedulers.io()) }
fun <T> Flowable<T>.computation() = compose { observeOn(Schedulers.computation()) }

fun createRetryNetObs(view: View?) = RxModalScreen.snackBar(view, R.string.error_connection_fail, R.string.alert_action_retry)
fun createRetryNetSingle(view: View?) = RxModalScreen.snackBarForSingle(view, R.string.error_connection_fail, R.string.alert_action_retry)

fun initMainThreadScheduler() = RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

fun checkMainThread(observer: Observer<*>): Boolean {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        observer.onSubscribe(Disposables.empty())
        observer.onError(IllegalStateException(
                "Expected to be called on the main thread but was " + Thread.currentThread().name))
        return false
    }
    return true
}

fun <T> Flowable<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher<T>(this)