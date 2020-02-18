package com.app.basemodule.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.basemodule.R
import com.app.basemodule.extensions.getStatusBarHeight
import com.app.basemodule.extensions.hideAnim
import com.app.basemodule.extensions.message
import com.app.basemodule.extensions.okButton
import com.app.basemodule.extensions.screenslog
import com.app.basemodule.extensions.showAlert
import com.app.basemodule.extensions.showAnim
import com.app.basemodule.extensions.toastNotImplemented
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

abstract class BaseFragment : Fragment(), BaseMVPView {
    protected val disposables = CompositeDisposable()
    private val viewCreationPublisher = PublishSubject.create<Boolean>()
    private var viewCreationDisposable: Disposable? = null
    abstract val layoutRes: Int
    open val TAG: String by lazy { simpleName() }

    protected fun simpleName(): String = this.javaClass.simpleName

    override fun showProgress(requestId: Int?) {
        activity?.runOnUiThread {
            view?.findViewById<View>(R.id.progress)?.showAnim()
        }
    }

    override fun hideProgress(requestId: Int?) {
        activity?.runOnUiThread {
            view?.findViewById<View>(R.id.progress)?.hideAnim()
        }
    }

    override fun manageDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun showErrorMessage(msg: CharSequence) {
        (activity as? BaseMVPView)?.showErrorMessage(msg)
    }

    override fun showSimpleDialog(msg: CharSequence) {
        activity?.showAlert {
            message(msg.toString())
            okButton { }
        }
    }

    override fun showNotImplementedYetMessage() {
        activity?.applicationContext?.toastNotImplemented()
    }

    override fun stringFromResources(resId: Int): String = getString(resId)

    override fun showErrorMessage(resId: Int) = showErrorMessage(getString(resId))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenslog(this.javaClass.canonicalName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = if (layoutRes <= 0) null else inflater.inflate(layoutRes, container, false)

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewCreationPublisher.hasObservers()) {
            viewCreationPublisher.onNext(true)
        }
    }

    protected fun waitForViewCreation(func: () -> Unit) {
        if (view != null) {
            func.invoke()
        } else {
            viewCreationDisposable = viewCreationPublisher.take(1).subscribe({ func.invoke() })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposeViewPublisher()
    }

    private fun disposeViewPublisher() {
        viewCreationDisposable?.dispose()
        viewCreationDisposable = null
    }

    protected fun setPaddingForStatusBar(needToSet: Boolean) {
        if (needToSet) {
            view?.findViewById<View>(com.app.basemodule.R.id.topContainer)?.setPadding(0, activity!!.getStatusBarHeight(), 0, 0)
        }
    }
}