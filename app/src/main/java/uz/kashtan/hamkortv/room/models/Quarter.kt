package uz.kashtan.hamkortv.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "streetAndQuarter")
data class Quarter (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Code")
    val code: String,

    var isSelected: Boolean = false
)
