package com.lbird.trendingapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lbird.trendingapp.data.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    repository: TrendingRepository
) : ViewModel() {
    val trendings = repository.getTrendings().asLiveData()
    val trendingUpdate = repository.updateTrendings().asLiveData()
}