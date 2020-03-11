package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.ComplaintModel

class ComplaintsNetworkDataSourceImpl(
    private val apiService: ApiService
) : ComplaintsNetworkDataSource {

    private var _downloadedComplaints: MutableLiveData<List<ComplaintModel>> = MutableLiveData()
    override val downloadedComplaints: LiveData<List<ComplaintModel>>
        get() = _downloadedComplaints

    override suspend fun fetchComplaint(codeClient: String, theme: String, text: String) {
        try {
            val fetchComplaint = apiService.getComplaintsAsync(codeClient, theme, text).await()
            _downloadedComplaints.postValue(fetchComplaint)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", " No Internet Connectivity", e)
        }
    }
}