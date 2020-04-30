package uz.kashtan.hamkortv.retrofit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.kashtan.hamkortv.internal.NoConnectivityException
import uz.kashtan.hamkortv.room.models.RatingModel
import uz.kashtan.hamkortv.room.models.RatingResponse

class SetRatingImpl(private val apiService: ApiService) : SetRating {
    private val _ratingResponse: MutableLiveData<RatingResponse> = MutableLiveData()

    override val ratingResponse: LiveData<RatingResponse>
        get() = _ratingResponse

    override suspend fun fetchRating(ratingModel: RatingModel) {
        try {
            val fetchedRating = apiService.setRating(ratingModel).await()
            _ratingResponse.postValue(fetchedRating)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", " No Internet Connectivity", e)
        }
    }
}