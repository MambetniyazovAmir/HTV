package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.RequestModel

class RequestNetworkDataSourceImpl(
    private val apiService: ApiService
) : RequestNetworkDataSource {
    private val _downloadedRequest: MutableLiveData<List<RequestModel>> = MutableLiveData()
    override val downloadedRequest: LiveData<List<RequestModel>>
        get() = _downloadedRequest

    override suspend fun fetchRequest(codeClient: String, theme: String) {
        try {
            val fetchRequest = apiService.getRequestMessageAsync(theme, codeClient).await()
            _downloadedRequest.postValue(fetchRequest)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", " No Internet Connectivity", e)
        }
    }
}