package uz.kashtan.hamkortv.room.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "requests")
data class RequestModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("Code")
    val code: String,
    @SerializedName("Message")
    val message: String
)