package uz.kashtan.hamkortv.ui.main.history.adapter

import uz.kashtan.hamkortv.room.models.LoginModel

interface ItemClickListener {
    fun onItemClick(model: LoginModel)
}