package com.mohamed.movieappcleanarchitecture.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.movieappcleanarchitecture.R
import com.mohamed.movieappcleanarchitecture.databinding.ActivityMainBinding
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.States
import com.mohamed.movieappcleanarchitecture.presentation.base.BaseActivity
import com.mohamed.movieappcleanarchitecture.presentation.movie_details.MovieDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : BaseActivity(), MoviesAdapter.OnMovieClickListener {
    private var binding: ActivityMainBinding? = null

    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)
        setContentView(binding?.root)

        setupRecyclerView()

        setupObserver()
        invokeApi()
    }

    private fun invokeApi() {
        lifecycleScope.launch {
            viewModel.movies.collect {
                moviesAdapter.submitData(it)
            }
        }
    }

    override fun setupObserver() {
        moviesAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading)
                showLoading()
//    binding?.progress?.isVisible=true
            else
//                binding?.progress?.isVisible=false
                cancelLoading()


        }
        lifecycleScope.launch {
            viewModel.movlies.collect { it ->
                when (it.states) {
                    States.LOADING -> showLoading()
                    States.SUCCESS -> {
                        cancelLoading()
//                        it.data?.results?.let { it1 -> moviesAdapter.submitData(it1) }
                    }
                    States.ERROR -> {
                        cancelLoading()
                        error(it.error?.message.toString())
                    }
                }
            }
        }
    }

    override fun cancelLoading() {
        binding?.showProgresBar = false
        binding?.executePendingBindings()

    }

    override fun showLoading() {
        binding?.showProgresBar = true
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter(this)
        binding?.moviesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onMovieClick(movieId: Int) {
        MovieDetailsActivity.newInstance(this, movieId)
    }
}