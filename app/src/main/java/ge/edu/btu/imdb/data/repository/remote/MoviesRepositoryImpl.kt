package ge.edu.btu.imdb.data.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import ge.edu.btu.imdb.data.paging.MoviesPagingSource
import ge.edu.btu.imdb.data.paging.SearchPagingSource
import ge.edu.btu.imdb.data.network.MoviesApiService
import ge.edu.btu.imdb.data.mapper.MoviesDTOToDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val moviesApiService: MoviesApiService,
    private val mapper: MoviesDTOToDomainMapper
) : MoviesRepository {

    private suspend fun getGenresMap(): Map<Int, String> {
        try {
            val genresResponse = moviesApiService.getGenres()
            val genres = genresResponse.body()?.genres ?: emptyList()
            return genres.associate { it.id to it.name }
        } catch (e: Exception) {
            return emptyMap()
        }
    }

    override fun getMoviesByCategory(category: String): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(moviesApiService, category) }
        ).flow
            .map {
                val genreMap = getGenresMap()
                it.map { movie ->
                    mapper.mapToDomainModel(movie, genreMap)
                }
            }
    }

    override fun searchMovies(query: String): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(moviesApiService, query) }
        ).flow
            .map {
                val genreMap = getGenresMap()
                it.map { movie ->
                    mapper.mapToDomainModel(movie, genreMap)
                }
            }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}