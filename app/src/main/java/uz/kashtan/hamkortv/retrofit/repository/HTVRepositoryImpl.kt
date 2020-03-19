package uz.kashtan.hamkortv.retrofit.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.kashtan.hamkortv.retrofit.network.StreetNetworkDataSource
import uz.kashtan.hamkortv.room.dao.StreetDao
import uz.kashtan.hamkortv.room.models.StreetOrQuarter

class HTVRepositoryImpl(
    private val streetDao: StreetDao,
    private val streetNetworkDataSource: StreetNetworkDataSource
) : HTVRepository {

    init {
        streetNetworkDataSource.downloadedStreets.observeForever { streetData ->
            persistFetchedStreetData(streetData)
        }
    }

    private fun persistFetchedStreetData(streetData: List<StreetOrQuarter>) {
        GlobalScope.launch(Dispatchers.IO) {
            streetDao.insertToDb(streetData)
        }
    }

    override suspend fun getStreet(): LiveData<List<StreetOrQuarter>> {
        return withContext(Dispatchers.IO) {
            return@withContext streetDao.getStreet()
        }
    }
}