package uz.kashtan.hamkortv.ui.dialog.house

import uz.kashtan.hamkortv.room.models.House

interface HouseDialogButtonClickListener {
    fun onHousePositiveButtonClick(house: House)
    fun onHouseNegativeButtonClick()
}