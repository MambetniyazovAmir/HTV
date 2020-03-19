package uz.kashtan.hamkortv.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.room.models.AuthModel

@Dao
interface AuthDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDb(authModel: List<AuthModel>)

    @Query("SELECT * FROM auth")
    fun getAuth(): LiveData<AuthModel>
}