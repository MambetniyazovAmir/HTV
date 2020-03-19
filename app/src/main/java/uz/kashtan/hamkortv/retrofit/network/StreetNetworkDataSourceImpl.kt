package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.StreetOrQuarter

class StreetNetworkDataSourceImpl(private val apiService: ApiService) : StreetNetworkDataSource {
    private val _downloadedStreets: MutableLiveData<List<StreetOrQuarter>> = MutableLiveData()
    override val downloadedStreets: LiveData<List<StreetOrQuarter>>
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