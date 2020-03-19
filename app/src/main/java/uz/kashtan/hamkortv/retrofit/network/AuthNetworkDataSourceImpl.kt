package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.AuthNetworkDataSource
import uz.kashtan.hamkortv.room.models.AuthModel

class AuthNetworkDataSourceImpl(
    private val apiService: ApiService
) : AuthNetworkDataSource {
    private var _downloadedAuth: MutableLiveData<List<AuthModel>> = MutableLiveData()
    override val downloadedAuth: LiveData<List<AuthModel>>
        get() = _downloadedAuth

    override suspend fun fetchAuth(dom: String, kvartal: String, kvartira: String, id: String) {
        try {
            val fetchedAuth = apiService
                .getAuthAsync(dom, kvartal, kvartira, id)
                .await()
            _downloadedAuth.postValue(fetchedAuth)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet connectivity", e)
        }
    }
}