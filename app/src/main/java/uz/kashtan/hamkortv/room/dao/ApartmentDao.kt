package uz.kashtan.hamkortv.room.dao

import androidx.room.Dao
import androidx.room.Query
import uz.kashtan.hamkortv.base.BaseDao
import uz.kashtan.hamkortv.room.models.Apartment

@Dao
interface ApartmentDao : BaseDao<Apartment> {

    @Query("SELECT * FROM apartments")
    fun getAllApartments(): List<Apartment>

    @Query("SELECT * FROM apartments WHERE id=:id")
    fun getApartmentById(id: Int): Apartment

    @Query("SELECT * FROM apartments WHERE codeHouse=:codeHouse")
    fun getApartmentByCodeHouse(codeHouse: String): Apartment

    @Query("SELECT * FROM apartments WHERE code=:codeApartment")
    fun getApartmentByCode(codeApartment: String): Apartment
}