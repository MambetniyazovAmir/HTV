package uz.kashtan.hamkortv.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.kashtan.hamkortv.room.daos.ChannelsDao
import uz.kashtan.hamkortv.room.daos.HistoryOfPaymentsDao
import uz.kashtan.hamkortv.room.models.ChannelModel
import uz.kashtan.hamkortv.room.models.HistoryOfPaymentsModel


@Database(
    entities = [ChannelModel::class, HistoryOfPaymentsModel::class],
    version = 1,
    exportSchema = false
)
abstract class AziyaTerminalDatabase : RoomDatabase() {

    abstract fun channelsDao(): ChannelsDao
    abstract fun historyOfPaymentsDao(): HistoryOfPaymentsDao

    companion object {
        private lateinit var INSTANCE: AziyaTerminalDatabase

        fun getInstance(context: Context): AziyaTerminalDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AziyaTerminalDatabase::class.java,
                    "AziyaTerminal.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

}