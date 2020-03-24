package uz.kashtan.hamkortv.ui.dialog.house

import uz.kashtan.hamkortv.room.models.House

interface HouseDialogItemClickListener {
    fun onItemClick(item: House, itemPosition: Int)
}