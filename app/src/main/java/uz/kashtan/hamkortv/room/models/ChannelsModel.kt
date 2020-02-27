package uz.kashtan.hamkortv.room.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "channels")
data class ChannelsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String
)