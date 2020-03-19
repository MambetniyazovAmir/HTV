package uz.kashtan.hamkortv.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Apartments")
data class Apartment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("CodeHouse")
    val codeHouse: String
)