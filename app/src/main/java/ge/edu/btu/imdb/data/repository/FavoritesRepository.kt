package ge.edu.btu.imdb.data.repository

import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun addFavoriteMovie(movie: MoviesDomainModel.ResultDomain)
    fun getAllFavoriteMovies(): Flow<List<MoviesDomainModel.ResultDomain>>
    suspend fun deleteFavoriteMovieById(movieId: Int)
    suspend fun isMovieFavorite(id: Int): Boolean
    fun getAllFavoriteMovieIds(): Flow<List<Int>>
}