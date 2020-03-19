package uz.kashtan.hamkortv.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.kashtan.hamkortv.room.dao.AuthDao
import uz.kashtan.hamkortv.room.dao.ChannelDao
import uz.kashtan.hamkortv.room.dao.LoginDao
import uz.kashtan.hamkortv.room.models.AuthModel
import uz.kashtan.hamkortv.room.models.ChannelsModel
import uz.kashtan.hamkortv.room.models.LoginModel

@Database(
    entities = [ChannelsModel::class, AuthModel::class, LoginModel::class], version = 1
)
abstract class HTVDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
    abstract fun authDao(): AuthDao
    abstract fun loginDao(): LoginDao

    companion object {
        @Volatile
        private var instance: HTVDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, HTVDatabase::class.java, "htv.db")
                .build()
    }
}