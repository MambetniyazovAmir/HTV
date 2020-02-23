package uz.kashtan.hamkortv.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historyOfPayments")
data class HistoryOfPaymentsModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 1,

    @ColumnInfo(name = "month")
    var month: String = "",

    @ColumnInfo(name = "dateOfPayment")
    var dateOfPayment: String = "",

    @ColumnInfo(name = "payment")
    var payment: Int = 0
)