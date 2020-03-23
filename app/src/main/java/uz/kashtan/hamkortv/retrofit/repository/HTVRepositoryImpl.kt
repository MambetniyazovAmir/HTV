package uz.kashtan.hamkortv.retrofit.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.kashtan.hamkortv.retrofit.network.QuarterNetworkDataSource
import uz.kashtan.hamkortv.room.dao.StreetDao
import uz.kashtan.hamkortv.room.models.Quarter

class HTVRepositoryImpl(
    private val streetDao: StreetDao,
    private val quarterNetworkDataSource: QuarterNetworkDataSource
) : HTVRepository {

    init {
        quarterNetworkDataSource.downloadedStreets.observeForever { streetData ->
            persistFetchedStreetData(streetData)
        }
    }

    private fun persistFetchedStreetData(streetData: List<Quarter>) {
        GlobalScope.launch(Dispatchers.IO) {
            streetDao.insertToDb(streetData)
        }
    }

    override suspend fun getStreet(): LiveData<List<Quarter>> {
        return withContext(Dispatchers.IO) {
            return@withContext streetDao.getStreet()
        }
    }
}