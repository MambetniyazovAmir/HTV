package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.Quarter

interface QuarterNetworkDataSource {
    val downloadedStreets: LiveData<List<Quarter>>

    suspend fun fetchStreets()
}