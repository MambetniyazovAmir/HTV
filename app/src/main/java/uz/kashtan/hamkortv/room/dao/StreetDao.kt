package uz.kashtan.hamkortv.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.room.models.StreetOrQuarter

@Dao
interface StreetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDb(streetOrQuarter: List<StreetOrQuarter>)

    @Query("SELECT * FROM streetAndQuarter")
    fun getStreet(): LiveData<List<StreetOrQuarter>>
}