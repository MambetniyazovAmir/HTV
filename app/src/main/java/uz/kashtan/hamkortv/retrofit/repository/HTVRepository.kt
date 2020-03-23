package uz.kashtan.hamkortv.retrofit.repository

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.Quarter

interface HTVRepository {
    suspend fun getStreet(): LiveData<List<Quarter>>
}