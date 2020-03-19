package uz.kashtan.hamkortv.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.kashtan.hamkortv.room.models.LoginModel

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToDB(loginModel: LoginModel)

    @Query("SELECT * FROM login")
    fun getLogin(): LiveData<LoginModel>
}