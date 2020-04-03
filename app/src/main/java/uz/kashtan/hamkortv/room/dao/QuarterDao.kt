package uz.kashtan.hamkortv.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.room.models.Quarter

@Dao
interface QuarterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDb(streetOrQuarter: List<Quarter>)

    @Query("SELECT * FROM quarters")
    fun getAllQuarters(): List<Quarter>

    @Query("SELECT * FROM quarters WHERE id = :id")
    fun getQuarterById(id: Int): Quarter

    @Query("SELECT * FROM quarters WHERE code = :codeQuarter")
    fun getQuarterByCode(codeQuarter: String): Quarter
}