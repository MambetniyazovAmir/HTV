package uz.kashtan.hamkortv.utils

import uz.kashtan.hamkortv.room.models.Apartment
import uz.kashtan.hamkortv.room.models.House
import uz.kashtan.hamkortv.room.models.Quarter

object DataHolder {
    var quarterList: List<Quarter> = arrayListOf()
    var houseList: List<House> = arrayListOf()
    var apartmentList: List<Apartment> = arrayListOf()
}