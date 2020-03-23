package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.House

class HouseNetworkDataSourceImpl(private val apiService: ApiService) : HouseNetworkDataSource {
    private val _downloadedHouses: MutableLiveData<List<House>> = MutableLiveData()
    override val downloadedHouses: LiveData<List<House>>
        get() = _downloadedHouses

    override suspend fun fetchHouses() {
        try {
            val fetched = apiService.getHousesAsync().await()
        } catch (e: NoConnectivityException){
            Log.e("Connectivity", "No Connectivity connection", e)
        }
    }
}