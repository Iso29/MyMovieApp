package com.example.mymovieapp.ui.movieList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.FragmentMovieListBinding
import com.example.mymovieapp.utils.ext.viewBinding
import com.example.mymovieapp.utils.response.NetworkResult
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment(
    R.layout.fragment_movie_list
) {
    private val movieAdapter = MovieAdapter { movie ->
        // navigate to detail with movie.id
    }
    private val binding by viewBinding(FragmentMovieListBinding::bind)
    private val viewModel by viewModels<MovieListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecycler.adapter = movieAdapter

        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.popularMovies.collect { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        movieAdapter.submitList(response.data.toDomainModel().movieList)
                    }

                    is NetworkResult.Error -> {
                        showErrorMessage(response.errorData.message + " : " + response.errorData.errorCode)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}