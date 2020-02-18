package com.app.basemodule.utils

import android.app.Activity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.app.basemodule.extensions.alert
import com.app.basemodule.extensions.okButton
import com.google.android.material.snackbar.Snackbar
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposables

object RxModalScreen {
    fun popupMenu(view: View, menuRes: Int): Observable<MenuItem> {
        return Observable.create { emitter ->
            val menu = PopupMenu(view.context, view)
            menu.inflate(menuRes)
            emitter.setDisposable(Disposables.fromAction {
                menu.setOnMenuItemClickListener(null)
                menu.dismiss()
            })
            menu.setOnMenuItemClickListener { item ->
                emitter.onNext(item)
                emitter.onComplete()
                return@setOnMenuItemClickListener true
            }
            menu.show()
        }
    }

    fun alert(
        context: Activity,
        title: Int,
        message: Int,
        okText: Int = android.R.string.ok
    ): Observable<Boolean> {
        return Observable.create { emitter ->
            val ad = context.alert {
                setTitle(title)
                setMessage(message)
                okButton(okText) {
                    emitter.onNext(true)
                    emitter.onComplete()
                }
            }
            emitter.setDisposable(Disposables.fromAction { ad.dismiss() })
            ad.show()
        }
    }

    fun alert(
        context: Activity,
        title: Int,
        message: String,
        okText: Int = android.R.string.ok
    ): Observable<Boolean> {
        return Observable.create { emitter ->
            val ad = context.alert {
                setTitle(title)
                setMessage(message)
                okButton(okText) {
                    emitter.onNext(true)
                    emitter.onComplete()
                }
            }
            emitter.setDisposable(Disposables.fromAction { ad.dismiss() })
            ad.show()
        }
    }

    fun snackBar(view: View?, resId: Int, actionName: Int = android.R.string.ok): Flowable<Boolean> {
        return if (view != null) {
            Flowable.create({ subscriber ->
                val snackBar = Snackbar.make(view, resId, Snackbar.LENGTH_INDEFINITE)
                snackBar.setAction(actionName) {
                    subscriber.onNext(true)
                    subscriber.onComplete()
                }
                snackBar.show()
            }, BackpressureStrategy.LATEST)
        } else {
            Flowable.just(false)
        }
    }

    fun snackBarForSingle(view: View?, resId: Int, actionName: Int = android.R.string.ok): Single<Boolean> {
        return if (view != null) {
            Single.create<Boolean> { subscriber ->
                val snackBar = Snackbar.make(view, resId, Snackbar.LENGTH_INDEFINITE)
                snackBar.setAction(actionName) {
                    subscriber.onSuccess(true)
                }
                snackBar.show()
            }
        } else {
            Single.just(false)
        }
    }

    //    fun dialog(context: Context, title: Int, message: Int, positive: Int = android.R.string.ok, negative: Int = android.R.string.cancel): Observable<Boolean> {
//        return Observable.create { subscriber: Subscriber<in Boolean> ->
//            val ad = AlertDialog.Builder(context).setTitle(title).setMessage(message)
//                    .setCancelable(false)
//                    .setPositiveButton(positive, { _, _ ->
//                        subscriber.onNext(true)
//                        subscriber.onCompleted()
//                    })
//                    .setNegativeButton(negative, { _, _ ->
//                subscriber.onNext(false)
//                subscriber.onCompleted()
//            }).create()
//            subscriber.add(Subscriptions.create { ad.dismiss() })
//            ad.show()
//        }
//    }
//
//
//    fun dialog(context: Context, title: String, message: String, positive: Int = android.R.string.ok, negative: Int = android.R.string.cancel): Observable<Boolean> {
//        return Observable.create { subscriber: Subscriber<in Boolean> ->
//            val ad = AlertDialog.Builder(context).setTitle(title).setMessage(message)
//                    .setCancelable(false)
//                    .setPositiveButton(positive, { _, _ ->
//                        subscriber.onNext(true)
//                        subscriber.onCompleted()
//                    }).setNegativeButton(negative, { _, _ ->
//                subscriber.onNext(false)
//                subscriber.onCompleted()
//            }).create()
//            subscriber.add(Subscriptions.create { ad.dismiss() })
//            ad.show()
//        }
//    }
//
}