package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.Quarter

interface StreetNetworkDataSource {
    val downloadedStreets: LiveData<List<Quarter>>

    suspend fun fetchStreets()
}