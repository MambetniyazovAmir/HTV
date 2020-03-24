package uz.kashtan.hamkortv.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "houses")
data class House(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("CodeQuarter")
    val codeQuarter: String,
    var isSelected: Boolean = false
)