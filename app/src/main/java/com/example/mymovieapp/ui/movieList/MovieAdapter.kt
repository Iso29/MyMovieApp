package com.example.mymovieapp.ui.movieList

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovieapp.data.model.movie.MovieDTO
import com.example.mymovieapp.databinding.ItemMovieBinding
import java.util.Locale

class MovieAdapter(
    private val onMovieClick: (MovieData) -> Unit = {}
) : ListAdapter<MovieData, MovieAdapter.MovieViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onMovieClick(getItem(position))
                }
            }
        }

        fun bind(movie: MovieData) = with(binding) {
            tvTitle.text = movie.title.orEmpty()

            tvRating.text = movie.rating
                ?.let { String.format(Locale.getDefault(), "%.1f", it) }
                ?: "–"

            Glide.with(binding.root)
                .load(IMAGE_BASE_URL+movie.posterUrl)
                .placeholder(R.drawable.ic_lock_power_off)
                .error(R.drawable.stat_notify_error)
                .into(binding.ivPoster)

//            ivPoster.load(movie.posterPath?.let { IMAGE_BASE_URL + it }) {
//                crossfade(true)
//                placeholder(R.drawable.ic_poster_placeholder)
//                error(R.drawable.ic_poster_placeholder)
//            }
        }
    }

    private companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

        val DiffCallback = object : DiffUtil.ItemCallback<MovieData>() {
            override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData) =
                oldItem == newItem
        }
    }
}