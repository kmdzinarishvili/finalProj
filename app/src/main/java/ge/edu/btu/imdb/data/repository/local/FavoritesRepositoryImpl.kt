package ge.edu.btu.imdb.data.repository.local

import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.data.local.dao.FavoriteMoviesDao
import ge.edu.btu.imdb.data.mapper.DomainToEntityMapper
import ge.edu.btu.imdb.domain.mapper.EntityToDomainMapper
import ge.edu.btu.imdb.domain.repository.local.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val favoriteMoviesDao: FavoriteMoviesDao,
    private val domainToEntityMapper: DomainToEntityMapper,
    private val entityToDomainMapper: EntityToDomainMapper,
) : FavoritesRepository {

    override suspend fun addFavoriteMovie(movie: MoviesDomainModel.ResultDomain) {
        val favoriteMovieEntity = domainToEntityMapper(movie)
        favoriteMoviesDao.insert(favoriteMovieEntity)
    }

    override fun getAllFavoriteMovies(): Flow<List<MoviesDomainModel.ResultDomain>> {
        return favoriteMoviesDao.getAllFavoriteMovies()
            .map {
                it.map {
                    entityToDomainMapper(it)
                }
            }
    }

    override suspend fun deleteFavoriteMovieById(movieId: Int) {
        favoriteMoviesDao.deleteMovieById(movieId)
    }

    override suspend fun isMovieFavorite(id: Int): Boolean {
        return favoriteMoviesDao.isMovieFavorite(id)
    }

    override fun getAllFavoriteMovieIds(): Flow<List<Int>> {
        return favoriteMoviesDao.getAllFavoriteMovieIds()
    }
}