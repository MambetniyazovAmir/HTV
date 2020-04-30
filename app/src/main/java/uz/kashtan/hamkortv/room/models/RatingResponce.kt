package uz.kashtan.hamkortv.room.models

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("Id")
    val requestId: String,
    @SerializedName("Rating")
    val rating: Int
)