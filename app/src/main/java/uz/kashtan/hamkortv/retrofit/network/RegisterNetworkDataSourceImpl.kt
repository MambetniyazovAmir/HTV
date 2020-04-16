package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.Abonent
import uz.kashtan.hamkortv.room.models.RegisterModel

class RegisterNetworkDataSourceImpl(private val apiService: ApiService) :
    RegisterNetworkDataSource {

    private var _downloadedRegistration: MutableLiveData<RegisterModel> = MutableLiveData()
    override val downloadedRegistration: LiveData<RegisterModel>
        get() = _downloadedRegistration

    override suspend fun fetchRegister(
        abonent: Abonent
    ) {
        try {
            val fetchRegistration =
                apiService.signUpAsync(abonent).await()
            _downloadedRegistration.postValue(fetchRegistration)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No connection", e)
        }
    }
}