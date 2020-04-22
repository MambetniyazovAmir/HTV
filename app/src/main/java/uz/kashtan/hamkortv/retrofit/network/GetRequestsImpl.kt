package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.Requests

class GetRequestsImpl(private val apiService: ApiService) : GetRequests {
    private val _downloadedRequests: MutableLiveData<List<Requests>> = MutableLiveData()
    override val downloadedRequests: LiveData<List<Requests>>
        get() = _downloadedRequests

    override suspend fun fetchRequests(id: String) {
        try {
            val fetchedRequests = apiService.getRequestsAsync(id).await()
            _downloadedRequests.postValue(fetchedRequests)
        } catch (e: NoConnectivityException){
            Log.e("Connectivity", "No Internet", e)
        }
    }
}