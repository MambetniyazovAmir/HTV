package uz.kashtan.hamkortv.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import uz.kashtan.hamkortv.utils.LiveEvent

/**
 * @author islomovs on 4/10/19
 * */
abstract class BaseViewModel : ViewModel() {
    val disposable: CompositeDisposable by lazy { CompositeDisposable() }
    val errorOther: LiveEvent<String> = LiveEvent()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}