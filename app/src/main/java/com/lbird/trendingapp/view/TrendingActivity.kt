package com.lbird.trendingapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbird.trendingapp.R
import com.lbird.trendingapp.databinding.ActivityTrendingBinding
import com.lbird.trendingapp.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_trending.*

@AndroidEntryPoint
class TrendingActivity : AppCompatActivity() {

    private val viewModel: TrendingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTrendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trendingAdapter = TrendingAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = trendingAdapter
                layoutManager = LinearLayoutManager( this@TrendingActivity)
            }

            viewModel.trendings.observe(this@TrendingActivity) { result ->
                trendingAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                imageNoInternet.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewTitleError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
                buttonRetry.isVisible = result is Resource.Error && result.data.isNullOrEmpty()

                buttonRetry.setOnClickListener {
                    binding.apply {
                        recyclerView.apply {
                            adapter = trendingAdapter
                            layoutManager = LinearLayoutManager( this@TrendingActivity)
                        }

                        viewModel.trendingUpdate.observe(this@TrendingActivity) { result ->
                            trendingAdapter.submitList(result.data)

                            progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                            imageNoInternet.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                            textViewTitleError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                            textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                            textViewError.text = result.error?.localizedMessage
                            buttonRetry.isVisible = result is Resource.Error && result.data.isNullOrEmpty()


                        }

                    }
                }

                swipeRefresh.setOnRefreshListener {

                    binding.apply {
                        recyclerView.apply {
                            adapter = trendingAdapter
                            layoutManager = LinearLayoutManager( this@TrendingActivity)
                        }

                        viewModel.trendingUpdate.observe(this@TrendingActivity) { result ->
                            trendingAdapter.submitList(result.data)

                            progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                            imageNoInternet.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                            textViewTitleError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                            textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                            textViewError.text = result.error?.localizedMessage
                            buttonRetry.isVisible = result is Resource.Error && result.data.isNullOrEmpty()

                            if (swipeRefresh.isRefreshing) {
                                swipeRefresh.isRefreshing = false
                            }

                        }

                    }
                }

            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.option_star -> Toast.makeText(this, "Sort by stars", Toast.LENGTH_SHORT).show()
            R.id.option_name -> Toast.makeText(this, "Sort by name", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}