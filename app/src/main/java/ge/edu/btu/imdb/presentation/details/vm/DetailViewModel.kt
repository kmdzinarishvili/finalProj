package ge.edu.btu.imdb.presentation.details.vm

import androidx.lifecycle.ViewModel
import ge.edu.btu.imdb.common.extension.viewModelScope
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.navigation.dashboard.DashboardNavigationApi
import ge.edu.btu.imdb.domain.usecase.local.AddFavoriteMovieUseCase
import ge.edu.btu.imdb.domain.usecase.local.CheckIfMovieIsFavoritesUseCase
import ge.edu.btu.imdb.domain.usecase.local.DeleteFavoriteMovieUseCase

class DetailViewModel(
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val checkIfMovieIsFavoritesUseCase: CheckIfMovieIsFavoritesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val navigationApi: DashboardNavigationApi
) : ViewModel() {

    private fun deleteFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        viewModelScope {
            deleteFavoriteMovieUseCase.invoke(movie.id)
        }
    }

    suspend fun isMovieInFavorites(movieId: Int): Boolean {
        return checkIfMovieIsFavoritesUseCase.invoke(movieId)
    }

    private fun addToFavorites(movie: MoviesDomainModel.ResultDomain) {
        viewModelScope {
            addFavoriteMovieUseCase.invoke(movie)
        }
    }

    suspend fun manageFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        val isFavorite = isMovieInFavorites(movie.id)
        if (isFavorite) {
            deleteFavoriteMovie(movie)
        } else {
            addToFavorites(movie)
        }
    }

    fun navigateToDashboard() {
        navigationApi.navigateToDashboard()
    }
}