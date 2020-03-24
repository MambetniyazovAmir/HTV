package uz.kashtan.hamkortv.ui.dialog.apartment

import uz.kashtan.hamkortv.room.models.Apartment

interface ApartmentDialogButtonClickListener {
    fun onApartmentPositiveButtonClick(apartment: Apartment)
    fun onApartmentNegativeButtonClick()
}