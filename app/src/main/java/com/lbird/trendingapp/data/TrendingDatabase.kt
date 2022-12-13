package com.lbird.trendingapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Trending::class], version = 2)
abstract class TrendingDatabase : RoomDatabase() {

    abstract fun trendingDao(): TrendingDao
}