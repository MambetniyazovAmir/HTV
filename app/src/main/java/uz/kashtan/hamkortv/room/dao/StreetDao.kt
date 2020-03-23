package uz.kashtan.hamkortv.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.room.models.Quarter

@Dao
interface StreetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDb(streetOrQuarter: List<Quarter>)

    @Query("SELECT * FROM streetAndQuarter")
    fun getStreet(): LiveData<List<Quarter>>
}