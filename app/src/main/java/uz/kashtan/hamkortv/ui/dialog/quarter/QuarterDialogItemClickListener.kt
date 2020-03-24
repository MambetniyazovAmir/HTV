package uz.kashtan.hamkortv.ui.dialog.quarter

import uz.kashtan.hamkortv.room.models.Quarter

interface QuarterDialogItemClickListener {
    fun onItemClick(item: Quarter, itemPosition: Int)
}