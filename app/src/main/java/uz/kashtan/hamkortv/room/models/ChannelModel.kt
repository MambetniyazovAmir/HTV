package uz.kashtan.hamkortv.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "channels")
data class ChannelModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 1,

    @ColumnInfo(name = "code")
    var code: String = "",

    @ColumnInfo(name = "channelName")
    var channelName: String = ""
)
