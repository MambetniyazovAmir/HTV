package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.RequestModel

interface RequestNetworkDataSource {

    val downloadedRequest: LiveData<List<RequestModel>>

    suspend fun fetchRequest(
        codeClient: String,
        theme: String
    )
}