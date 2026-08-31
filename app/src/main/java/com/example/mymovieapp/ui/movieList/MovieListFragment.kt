package com.example.mymovieapp.ui.movieList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mymovieapp.databinding.FragmentMovieListBinding
import com.example.mymovieapp.utils.ext.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private val binding by viewBinding(FragmentMovieListBinding::bind)

    private val viewModel by viewModels<MovieListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllMovies()
    }

}