package uz.kashtan.hamkortv.retrofit.network

import androidx.lifecycle.LiveData
import uz.kashtan.hamkortv.room.models.AuthModel

interface AuthNetworkDataSource {
    val downloadedAuth: LiveData<List<AuthModel>>

    suspend fun fetchAuth(
        dom: String,
        kvartal: String,
        kvartira: String,
        id: String,
        token: String
    )
}