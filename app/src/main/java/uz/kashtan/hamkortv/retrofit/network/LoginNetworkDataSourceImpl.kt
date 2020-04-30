package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.retrofit.network.ApiService
import uz.kashtan.hamkortv.retrofit.network.LoginNetworkDataSource
import uz.kashtan.hamkortv.room.models.LoginModel

class LoginNetworkDataSourceImpl(
    private var apiService: ApiService
) : LoginNetworkDataSource {
    private var _downloadedLogin: MutableLiveData<List<LoginModel>> = MutableLiveData()
    override val downloadedLogin: LiveData<List<LoginModel>>
        get() = _downloadedLogin

    override suspend fun fetch(year: String, codeClient: String) {
        try {
            val fetchLogin = apiService.getLoginAsync(codeClient, year).await()
            _downloadedLogin.postValue(fetchLogin)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connectivity", e)
        }
    }
}