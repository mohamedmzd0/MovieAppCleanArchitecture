package com.mohamed.movieappcleanarchitecture.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamed.movieappcleanarchitecture.R
import com.mohamed.movieappcleanarchitecture.databinding.ActivityMainBinding
import com.mohamed.movieappcleanarchitecture.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : BaseActivity() {
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
        viewModel.getMovies()
    }

    override fun setupObserver() {
        lifecycleScope.launch {
            viewModel.state.collect {
//moviesAdapter.setAll(it.data)
        }
    }
}

override fun cancelLoading() {

}

override fun showLoading() {

}

private fun setupRecyclerView() {
    moviesAdapter = MoviesAdapter()
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
}