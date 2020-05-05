package uz.kashtan.hamkortv.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.kashtan.hamkortv.room.models.Requests

@Dao
interface RequestsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDb(requests: List<Requests>)

    @Delete
    fun deleteAll(data: List<Requests>)

    @Query("SELECT * FROM getRequests")
    fun getRequests(): List<Requests>

    @Query("Select * FROM getRequests WHERE requestId =:requestId")
    fun getRequestById(requestId: String): Requests

    @Update
    fun setClicked(requestModel: Requests)
}