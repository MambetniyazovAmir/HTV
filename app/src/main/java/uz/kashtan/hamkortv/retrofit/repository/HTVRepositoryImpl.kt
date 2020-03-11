package uz.kashtan.hamkortv.retrofit.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.retrofit.network.AuthNetworkDataSource
import uz.kashtan.hamkortv.room.dao.AuthDao
import uz.kashtan.hamkortv.room.models.AuthModel

class HTVRepositoryImpl(
    private val userData: AuthDao,
    private val authNetworkDataSource: AuthNetworkDataSource
) : HTVRepository {

    init {
        authNetworkDataSource.downloadedAuth.observeForever { userData ->

        }
    }

    override suspend fun getUserData(): LiveData<AuthModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun persistFetchedUserData(fetchedUserData: AuthModel){
        GlobalScope.launch {

        }
    }
}