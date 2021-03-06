package com.nurzainpradana.androidfundamental.submission1moviecataloguetesting.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurzainpradana.androidfundamental.submission1moviecataloguetesting.data.Movie
import com.nurzainpradana.androidfundamental.submission1moviecataloguetesting.databinding.FragmentMoviesBinding
import com.nurzainpradana.androidfundamental.submission1moviecataloguetesting.ui.detailmovie.DetailMovieActivity
import java.util.*

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieViewModel = ViewModelProvider(this, NewInstanceFactory()).get(MovieViewModel::class.java)
        val movies = movieViewModel.getListMovie()
        showRecyclerCardView(movies)
    }

    private fun showRecyclerCardView(movies: ArrayList<Movie>) {
        with(binding) {
            rvMovies.setHasFixedSize(true)
            rvMovies.layoutManager = LinearLayoutManager(activity)

            val cardViewMovieAdapter = CardViewMovieAdapter(movies)
            rvMovies.adapter = cardViewMovieAdapter

            cardViewMovieAdapter.setOnItemClickCallback(object : CardViewMovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    showSelectedMovie(data)

                    val gotoDetailMovie = Intent(context, DetailMovieActivity::class.java)
                    gotoDetailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.movieId)

                    startActivity(gotoDetailMovie)
                }
            })
        }
    }

    private fun showSelectedMovie(movie: Movie) {
        Toast.makeText(context, "Kamu memilih " + movie.movieTitle, Toast.LENGTH_SHORT).show()
    }
}