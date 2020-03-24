package uz.kashtan.hamkortv.ui.dialog.apartment

import uz.kashtan.hamkortv.room.models.Apartment

interface ApartmentDialogItemClickListener {
    fun onItemClick(item: Apartment, itemPosition: Int)
}