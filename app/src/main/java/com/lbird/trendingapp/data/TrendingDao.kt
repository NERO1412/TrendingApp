package com.lbird.trendingapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingDao {

    @Query( "SELECT * FROM trendings")
    fun getAllTrendings(): Flow<List<Trending>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendings(restaurants: List<Trending>)

    @Query("DELETE FROM trendings")
    suspend fun deleteAllTrendings()

}