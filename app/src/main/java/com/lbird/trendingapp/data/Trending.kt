package com.lbird.trendingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trendings")
data class Trending(
    @PrimaryKey val username: String,
    val repositoryName: String,
    //val avatar: String,
    //val description: String, ->have some NULL value
    val url: String,
    //val language: String, ->have some NULL value
    val totalStars: String,
    val forks: String,
    var visibility: Boolean = false
)