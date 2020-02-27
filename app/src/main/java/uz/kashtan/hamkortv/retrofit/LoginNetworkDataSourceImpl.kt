package uz.kashtan.hamkortv.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.LoginModel

class LoginNetworkDataSourceImpl(
    private var apiService: ApiService
) : LoginNetworkDataSource {
    private var _downloadedLogin: MutableLiveData<List<LoginModel>> = MutableLiveData()
    override val downloadedLogin: LiveData<List<LoginModel>>
        get() = _downloadedLogin

    override suspend fun fetch(year: String, codeClient: String) {
        try {
            val fetchLogin = apiService.getLoginAsync(year, codeClient).await()
            _downloadedLogin.postValue(fetchLogin)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connectivity", e)
        }
    }
}