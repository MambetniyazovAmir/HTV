package uz.kashtan.hamkortv.room.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "auth")
data class AuthModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("Code")
    val code: String,
    @SerializedName("CodeInfo")
    val codeInfo: String,
    @SerializedName("Message")
    val message: String,
    @SerializedName("FIOMaster")
    val name: String,
    @SerializedName("Photo")
    val photo: String
)