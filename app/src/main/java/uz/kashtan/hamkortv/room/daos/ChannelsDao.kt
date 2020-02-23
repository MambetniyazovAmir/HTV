package uz.kashtan.hamkortv.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.room.models.ChannelModel


@Dao
interface ChannelsDao {
    @Query("SELECT * FROM channels")
    fun getChannels(): LiveData<List<ChannelModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDB(models: List<ChannelModel>)
}