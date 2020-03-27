package uz.kashtan.hamkortv.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.kashtan.hamkortv.room.dao.*
import uz.kashtan.hamkortv.room.models.*

@Database(
    entities = [ChannelsModel::class, AuthModel::class, LoginModel::class, Quarter::class, Apartment::class, House::class], version = 1
)
abstract class HTVDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
    abstract fun authDao(): AuthDao
    abstract fun loginDao(): LoginDao
    abstract fun houseDao(): HouseDao
    abstract fun apartmentDao(): ApartmentDao
    abstract fun quarterDao(): QuarterDao

    companion object {
        @Volatile
        private var instance: HTVDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, HTVDatabase::class.java, "htv.db")
                .allowMainThreadQueries()
                .build()

    }
}