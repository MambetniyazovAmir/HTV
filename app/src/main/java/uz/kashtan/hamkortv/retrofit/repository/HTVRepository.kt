package uz.kashtan.hamkortv.retrofit.repository

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.Apartment
import uz.kashtan.hamkortv.room.models.AuthModel
import uz.kashtan.hamkortv.room.models.House
import uz.kashtan.hamkortv.room.models.StreetOrQuarter
import java.lang.Appendable

interface HTVRepository {
    suspend fun getStreet(): LiveData<List<StreetOrQuarter>>
}