package uz.kashtan.hamkortv.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "houses")
data class House(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("Name")
    @ColumnInfo(name = "name")
    val name: String,
    @SerializedName("Code")
    @ColumnInfo(name = "code")
    val code: String,
    @SerializedName("CodeQuarter")
    @ColumnInfo(name = "codeQuarter")
    val codeQuarter: String,
    var isSelected: Boolean = false
)