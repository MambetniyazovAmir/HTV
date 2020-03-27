package uz.kashtan.hamkortv.retrofit.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.kashtan.hamkortv.retrofit.network.QuarterNetworkDataSource
import uz.kashtan.hamkortv.room.dao.QuarterDao
import uz.kashtan.hamkortv.room.models.Quarter

class HTVRepositoryImpl(
    private val quarterDao: QuarterDao,
    private val quarterNetworkDataSource: QuarterNetworkDataSource
) : HTVRepository {

    init {
        quarterNetworkDataSource.downloadedStreets.observeForever { streetData ->
            persistFetchedStreetData(streetData)
        }
    }

    private fun persistFetchedStreetData(streetData: List<Quarter>) {
        GlobalScope.launch(Dispatchers.IO) {
            quarterDao.insertToDb(streetData)
        }
    }

}