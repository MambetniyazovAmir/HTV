package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.StreetOrQuarter

interface StreetNetworkDataSource {
    val downloadedStreets: LiveData<List<StreetOrQuarter>>

    suspend fun fetchStreets()
}