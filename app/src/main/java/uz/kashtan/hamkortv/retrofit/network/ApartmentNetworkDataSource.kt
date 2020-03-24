package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.Apartment

interface ApartmentNetworkDataSource {

    val downloadedApartment: LiveData<List<Apartment>>

    suspend fun fetchApartments()
}