package uz.kashtan.hamkortv.ui.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.kashtan.hamkortv.retrofit.repository.HTVRepository

class AddressViewModelFactory(private val htvRepository: HTVRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return AddressViewModel(htvRepository) as T
    }
}