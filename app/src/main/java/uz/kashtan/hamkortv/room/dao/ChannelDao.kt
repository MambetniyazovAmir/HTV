package uz.kashtan.hamkortv.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.room.models.ChannelsModel

@Dao
interface ChannelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDB(channelsModel: ChannelsModel)

    @Query("SELECT * FROM channels")
    fun getChannels(): LiveData<ChannelsModel>
}