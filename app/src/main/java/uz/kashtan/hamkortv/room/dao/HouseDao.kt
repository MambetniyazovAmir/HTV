package uz.kashtan.hamkortv.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.base.BaseDao
import uz.kashtan.hamkortv.room.models.House
import uz.kashtan.hamkortv.room.models.Quarter

@Dao
interface HouseDao: BaseDao<House> {

    @Query("SELECT * FROM houses")
    fun getAllHouses(): List<House>

    @Query("SELECT * FROM houses WHERE id=:id")
    fun getHouseById(id: Int): House

    @Query("SELECT * FROM houses WHERE codeQuarter=:codeQuarter")
    fun getHouseByCodeQuarter(codeQuarter: String): House

    @Query("SELECT * FROM houses WHERE code = :codeHouse")
    fun getHouseByCode(codeHouse: String): House
}