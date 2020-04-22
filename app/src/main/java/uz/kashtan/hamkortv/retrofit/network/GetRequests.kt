package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.Requests

interface GetRequests {
    val downloadedRequests: LiveData<List<Requests>>
    suspend fun fetchRequests(id: String)
}