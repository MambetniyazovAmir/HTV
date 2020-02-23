package uz.kashtan.hamkortv.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.room.models.HistoryOfPaymentsModel


@Dao
interface HistoryOfPaymentsDao {
    @Query("SELECT * FROM historyOfPayments")
    fun getHistoryOfPayments(): LiveData<List<HistoryOfPaymentsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDB(models: List<HistoryOfPaymentsModel>)
}