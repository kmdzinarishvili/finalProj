package ge.edu.btu.imdb.dashboardimpl.feature.favorites.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import ge.edu.btu.imdb.corecommon.extensions.viewModelScope
import ge.edu.btu.imdb.coredomain.remote.MoviesDomainModel
import ge.edu.btu.imdb.corepresentation.constants.MovieItemKeyConstant
import ge.edu.btu.imdb.coredatabase.domain.usecase.DeleteFavoriteMovieUseCase
import ge.edu.btu.imdb.coredatabase.domain.usecase.GetFavoriteMoviesUseCase
import ge.edu.btu.imdb.detailapi.DetailNavigationApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class FavoritesViewModel(
    private val navigationApi: DetailNavigationApi,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase
) : ViewModel() {

    private val _favoriteMovies =
        MutableStateFlow<List<MoviesDomainModel.ResultDomain>>(emptyList())
    val favoriteMovies: StateFlow<List<MoviesDomainModel.ResultDomain>> get() = _favoriteMovies

    init {
        fetchFavoriteMovies()
    }

    private fun fetchFavoriteMovies() {
        viewModelScope {
            getFavoriteMoviesUseCase.invoke().collectLatest {
                _favoriteMovies.value = it
            }
        }
    }

    fun deleteFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        viewModelScope {
            deleteFavoriteMovieUseCase.invoke(movie.id)
        }
    }
    fun navigateToDetails(item: MoviesDomainModel.ResultDomain) {
        val bundle = Bundle().apply {
            putParcelable(MovieItemKeyConstant.MOVIE_ITEM_KEY, item)
        }
        navigationApi.navigateToDetail(bundle)
    }
}