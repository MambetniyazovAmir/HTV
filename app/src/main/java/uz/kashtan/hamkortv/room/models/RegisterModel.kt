package uz.kashtan.hamkortv.room.models

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @SerializedName("ID")
    val userId: String,
    @SerializedName("Total")
    val totalSum: String,
    @SerializedName("Error")
    val message: String
)