package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.Apartment

class ApartmentNetworkDataSourceImpl(private val apiService: ApiService) : ApartmentNetworkDataSource {
    private val _downloadedApartment: MutableLiveData<List<Apartment>> = MutableLiveData()
    override val downloadedApartment: LiveData<List<Apartment>>
        get() = _downloadedApartment

    override suspend fun fetchApartments() {

        try {
            val fetched = apiService.getApartmentsAsync().await()
            _downloadedApartment.postValue(fetched)
        } catch (e: NoConnectivityException) {

            Log.e("Connectivity", "No Connectivity connection", e)
        }
    }
}