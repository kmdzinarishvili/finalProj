package ge.edu.btu.imdb.detailimpl.viewmodel

import androidx.lifecycle.ViewModel
import ge.edu.btu.imdb.corecommon.extensions.viewModelScope
import ge.edu.btu.imdb.coredomain.remote.MoviesDomainModel
import ge.edu.btu.imdb.dashboardapi.DashboardNavigationApi
import ge.edu.btu.imdb.coredatabase.domain.usecase.AddFavoriteMovieUseCase
import ge.edu.btu.imdb.coredatabase.domain.usecase.CheckIfMovieIsFavoritesUseCase
import ge.edu.btu.imdb.coredatabase.domain.usecase.DeleteFavoriteMovieUseCase

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