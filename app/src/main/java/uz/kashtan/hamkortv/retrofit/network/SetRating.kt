package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.RatingModel
import uz.kashtan.hamkortv.room.models.RatingResponse

interface SetRating {
    val ratingResponse: LiveData<RatingResponse>
    suspend fun fetchRating(
        ratingModel: RatingModel
    )
}