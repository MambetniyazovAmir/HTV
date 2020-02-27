package uz.kashtan.hamkortv.retrofit

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.LoginModel

interface LoginNetworkDataSource {
    val downloadedLogin: LiveData<List<LoginModel>>

    suspend fun fetch(
        year: String,
        codeClient: String
    )
}