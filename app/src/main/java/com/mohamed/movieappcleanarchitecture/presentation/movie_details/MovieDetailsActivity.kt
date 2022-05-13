package com.mohamed.movieappcleanarchitecture.presentation.movie_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.mohamed.movieappcleanarchitecture.R
import com.mohamed.movieappcleanarchitecture.databinding.ActivityMovieDetailsBinding
import com.mohamed.movieappcleanarchitecture.infrastructure.utils.States
import com.mohamed.movieappcleanarchitecture.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity() {
    companion object {
        fun newInstance(context: Context, movieID: Int) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("movie_id", movieID)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityMovieDetailsBinding

    private val viewModel by viewModels<MovieDetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.activity_movie_details, null, false)
        setContentView(binding.root)
        setupObserver()
    }


    override fun setupObserver() {
        lifecycleScope.launchWhenResumed {
            viewModel.getMovie(id = intent.getIntExtra("movie_id", -1)).collect {
                if (it.states == States.SUCCESS) {
                    binding.movie = it.data
                }
            }
        }
    }

    override fun cancelLoading() {

    }

    override fun showLoading() {

    }
}