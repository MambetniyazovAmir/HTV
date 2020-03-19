package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.ChannelsNetworkDataSource
import uz.kashtan.hamkortv.room.models.ChannelsModel

class ChannelsNetworkDataSourceImpl(
    private val apiService: ApiService
) : ChannelsNetworkDataSource {
    private val _downloadedChannels = MutableLiveData<List<ChannelsModel>>()
    override val downloadedChannels: LiveData<List<ChannelsModel>>
        get() = _downloadedChannels

    override suspend fun fetchChannels() {
        try {
            val fetchedChannels = apiService
                .getChannelsAsync()
                .await()
            _downloadedChannels.postValue(fetchedChannels)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet connection", e)
        }
    }
}