package uz.kashtan.hamkortv.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: T)

    @Delete
    fun delete(model: T)

}