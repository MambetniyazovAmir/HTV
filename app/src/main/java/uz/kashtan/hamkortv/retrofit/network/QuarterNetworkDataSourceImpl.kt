package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.Quarter

class QuarterNetworkDataSourceImpl(private val apiService: ApiService) : QuarterNetworkDataSource {
    private val _downloadedStreets: MutableLiveData<List<Quarter>> = MutableLiveData()
    override val downloadedStreets: LiveData<List<Quarter>>
        get() = _downloadedStreets

    override suspend fun fetchStreets() {
        try {
            val fetchedStreets = apiService.getQuartersAsync().await()
            _downloadedStreets.postValue(fetchedStreets)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Connectivity connection", e)
        }
    }
}