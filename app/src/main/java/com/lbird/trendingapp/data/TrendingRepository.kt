package com.lbird.trendingapp.data

import androidx.room.withTransaction
import com.lbird.trendingapp.api.TrendingApi
import com.lbird.trendingapp.utilities.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val api: TrendingApi,
    private val db: TrendingDatabase
) {
    private val trendingDao = db.trendingDao()

    fun getTrendings() = networkBoundResource(
        query = {
            trendingDao.getAllTrendings()
        },
        fetch = {
            delay(2000)
            api.getTrendings()
        },
        saveFetchResult = { trendings ->
            db.withTransaction {
                trendingDao.deleteAllTrendings()
                trendingDao.insertTrendings(trendings)
            }
        }
    )

    fun updateTrendings() = networkBoundResource(
        query = {
            trendingDao.getAllTrendings()
        },
        fetch = {
            delay(2000)
            api.getTrendings()
        },
        saveFetchResult = { trendings ->
            db.withTransaction {
                trendingDao.deleteAllTrendings()
                trendingDao.insertTrendings(trendings)
            }
        }
    )
}