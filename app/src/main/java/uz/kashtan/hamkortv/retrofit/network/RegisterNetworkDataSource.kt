package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.Abonent
import uz.kashtan.hamkortv.room.models.RegisterModel

interface RegisterNetworkDataSource {

    val downloadedRegistration: LiveData<RegisterModel>

    suspend fun fetchRegister(
        abonent: Abonent
    )
}