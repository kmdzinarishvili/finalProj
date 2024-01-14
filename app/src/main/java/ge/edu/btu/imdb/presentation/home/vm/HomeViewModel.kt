package ge.edu.btu.imdb.presentation.home.vm

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import ge.edu.btu.imdb.common.extension.viewModelScope
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.common.constants.MovieItemKeyConstant
import ge.edu.btu.imdb.domain.usecase.remote.GetMovieUseCase
import ge.edu.btu.imdb.domain.usecase.local.AddFavoriteMovieUseCase
import ge.edu.btu.imdb.domain.usecase.local.CheckIfMovieIsFavoritesUseCase
import ge.edu.btu.imdb.domain.usecase.local.DeleteFavoriteMovieUseCase
import ge.edu.btu.imdb.dashboardimpl.container.domain.usecase.remote.SearchMovieUseCase
import ge.edu.btu.imdb.presentation.enums.MovieCategory
import ge.edu.btu.imdb.domain.usecase.local.GetAllFavoriteMovieIdsUseCase
import ge.edu.btu.imdb.navigation.detail.DetailNavigationApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val navigationApi: DetailNavigationApi,
    private val getMovieUseCase: GetMovieUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val checkIfMovieIsFavoritesUseCase: CheckIfMovieIsFavoritesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val getAllFavoriteMovieIdsUseCase: GetAllFavoriteMovieIdsUseCase
) : ViewModel() {

    private val _movies =
        MutableStateFlow<PagingData<MoviesDomainModel.ResultDomain>>(PagingData.empty())
    val movies: StateFlow<PagingData<MoviesDomainModel.ResultDomain>> = _movies

    private val _favoriteMovieIds: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    val favoriteMovieIds: StateFlow<List<Int>> = _favoriteMovieIds

    init {
        getMovies(MovieCategory.POPULAR.category)
        viewModelScope.launch {
            getAllFavoriteMovieIdsUseCase.invoke()
                .collect { ids ->
                    _favoriteMovieIds.value = ids
                }
        }
    }

    fun getMovies(category: String) {
        viewModelScope.launch {
            getMovieUseCase.invoke(category)
                .map { pagingData ->
                    pagingData.map { movie ->
                        val isFavorite = movie.id in (favoriteMovieIds.firstOrNull() ?: emptyList())
                        movie.isFavorite = isFavorite
                        movie
                    }
                }
                .cachedIn(viewModelScope)
                .collectLatest { moviesData ->
                    _movies.value = moviesData
                }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            searchMovieUseCase.searchMovies(query)
                .map { result ->
                    result.map { movie ->
                        val isFavorite = movie.id in (favoriteMovieIds.firstOrNull() ?: emptyList())
                        movie.isFavorite = isFavorite
                        movie
                    }
                }
                .cachedIn(viewModelScope)
                .collectLatest { searchData ->
                    _movies.value = searchData
                }
        }
    }

    private fun deleteFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        viewModelScope {
            deleteFavoriteMovieUseCase.invoke(movie.id)
        }
    }

    private suspend fun isMovieInFavorites(movieId: Int): Boolean {
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

    fun navigateToDetail(item: MoviesDomainModel.ResultDomain) {
        val bundle = Bundle().apply {
            putParcelable(MovieItemKeyConstant.MOVIE_ITEM_KEY, item)
        }
        navigationApi.navigateToDetail(bundle)
    }
}