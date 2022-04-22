package com.mohamed.movieappcleanarchitecture.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohamed.movieappcleanarchitecture.R
import com.mohamed.movieappcleanarchitecture.databinding.ItemMoviesBinding
import com.mohamed.movieappcleanarchitecture.domain.main_movies.entity.MoivesResponse
import com.mohamed.movieappcleanarchitecture.domain.movie_details.entity.MovieDetailsResponse

class MoviesAdapter constructor(private val onMovieClickListener: OnMovieClickListener) :
    PagingDataAdapter<MovieDetailsResponse, MoviesAdapter.MoviesViewHolder>(diffCallback = object :
        DiffUtil.ItemCallback<MovieDetailsResponse>() {
        override fun areItemsTheSame(oldItem: MovieDetailsResponse, newItem: MovieDetailsResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieDetailsResponse, newItem: MovieDetailsResponse): Boolean {
            return oldItem.equals(newItem)
        }
    }) {


    inner class MoviesViewHolder(val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition == -1)
                    return@setOnClickListener
                getItem(bindingAdapterPosition)?.id?.let { it1 ->
                    onMovieClickListener.onMovieClick(
                        it1
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movies,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        holder.binding.movie = getItem(position)

    }

    interface OnMovieClickListener {
        fun onMovieClick(movieId: Int)
    }
}

