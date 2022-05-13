package com.mohamed.movieappcleanarchitecture.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
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
        onRefresh()
        setupObserver()
        invokeApi()
    }

    private fun invokeApi() {
        lifecycleScope.launchWhenResumed {
            viewModel.movies.collect {
                moviesAdapter.submitData(it)
                Log.d(TAG, "invokeApi: size of list ${moviesAdapter.itemCount}")

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
        lifecycleScope.launchWhenResumed {
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

    private fun onRefresh() {
        binding?.btnRefresh?.setOnClickListener { moviesAdapter.refresh() }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onMovieClick(movieId: Int) {
        MovieDetailsActivity.newInstance(this, movieId)
    }
}


/*
data class SomeOne(var name: String, var age: Int, var job: String)

fun main() {
    val list = listOf(
        SomeOne("Ali", 20, "web developer"),
        SomeOne("Ahmed", 10, "Tester"),
        SomeOne("Mohamed", 18, "Ios Developer"),
        SomeOne("Mahmoud", 24, "Android developer"),
    )
    sort(list)
    filter(list)
    map(list)
}

fun sort(list: List<SomeOne>) {
    println("sorting")
    // re order current list by numbers or alphabitics
    val sortedList = list.sortedByDescending { it.age }
//    val sortedList = list.sortedBy { it.age }
    println(sortedList)
}

fun map(list: List<SomeOne>) {
    println("mapping")
// create only list of ages
    val mappedList = list.map { it.age }
    println(mappedList)
}

fun filter(list: List<SomeOne>) {
    println("filtering")
// get only members with age >= 20
    val mappedList = list.filter { it.age >= 20 }
    println(mappedList)
}

fun reduce() {

}

fun combination() {

}*/
