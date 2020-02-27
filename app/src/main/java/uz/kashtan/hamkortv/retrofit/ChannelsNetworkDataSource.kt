package uz.kashtan.hamkortv.retrofit

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.ChannelsModel

interface ChannelsNetworkDataSource {
    val downloadedChannels: LiveData<List<ChannelsModel>>

    suspend fun fetchChannels()
}