package uz.kashtan.hamkortv.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "apartments")
data class Apartment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("Name")
    @ColumnInfo(name = "name")
    val name: String,
    @SerializedName("Code")
    @ColumnInfo(name = "code")
    val code: String,
    @SerializedName("CodeHouse")
    @ColumnInfo(name = "codeHouse")
    val codeHouse: String,
    var isSelected: Boolean = false
)