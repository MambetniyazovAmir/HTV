package uz.kashtan.hamkortv.ui.dialog

import androidx.lifecycle.ViewModel
import uz.kashtan.hamkortv.internal.lazyDeferred
import uz.kashtan.hamkortv.retrofit.repository.HTVRepository

class AddressViewModel(
    private val htvRepository: HTVRepository
) : ViewModel() {
    val street  by lazyDeferred { htvRepository.getStreet() }
}