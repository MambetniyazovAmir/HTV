package uz.kashtan.hamkortv.retrofit.repository

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.AuthModel

interface HTVRepository {
    suspend fun getUserData(): LiveData<AuthModel>
}