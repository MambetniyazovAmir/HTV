package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.ComplaintModel

interface ComplaintsNetworkDataSource {
    val downloadedComplaints: LiveData<List<ComplaintModel>>

    suspend fun fetchComplaint(
        codeClient: String,
        theme: String,
        text: String
    )
}