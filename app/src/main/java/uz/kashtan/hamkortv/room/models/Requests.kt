package uz.kashtan.hamkortv.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "getRequests")
data class Requests(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("Id")
    @ColumnInfo(name = "requestId")
    val requestId: String,
    @SerializedName("Date")
    @ColumnInfo(name = "date")
    val date: String,
    @SerializedName("Theme")
    @ColumnInfo(name = "theme")
    val theme: String,
    @SerializedName("Status")
    @ColumnInfo(name = "status")
    val status: String,
    @SerializedName("Title")
    @ColumnInfo(name = "title")
    val title: String,
    @SerializedName("Rating")
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "clicked")
    var clicked: Boolean
)