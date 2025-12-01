package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.test_lab_week_12.model.Movie

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "17a599d04830d82a43ece97249a302dc"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData

    suspend fun fetchMovies() {
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (exception: Exception) {
            errorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}