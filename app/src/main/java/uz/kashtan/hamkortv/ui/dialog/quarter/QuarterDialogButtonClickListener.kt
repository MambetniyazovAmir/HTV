package uz.kashtan.hamkortv.ui.dialog.quarter

import uz.kashtan.hamkortv.room.models.Quarter

interface QuarterDialogButtonClickListener {
    fun onPositiveButtonClick(quarter: Quarter)
    fun onNegativeButtonClick()
}