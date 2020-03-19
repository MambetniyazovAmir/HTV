package uz.kashtan.hamkortv.room.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "login")
data class LoginModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("Code")
    val code: String,
    @SerializedName("DateOfTime")
    val dateOfTime: String,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Month")
    val month: String,
    @SerializedName("Sum")
    val sum: String,
    @SerializedName("Color")
    val color: String
)