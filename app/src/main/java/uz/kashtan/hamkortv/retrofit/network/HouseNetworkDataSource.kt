package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.House

interface HouseNetworkDataSource {

    val downloadedHouses: LiveData<List<House>>

    suspend fun fetchHouses()
}