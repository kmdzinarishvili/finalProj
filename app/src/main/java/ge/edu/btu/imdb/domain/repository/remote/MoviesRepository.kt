package ge.edu.btu.imdb.domain.repository.remote

import androidx.paging.PagingData
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesByCategory(category: String): Flow<PagingData<MoviesDomainModel.ResultDomain>>
    fun searchMovies(query: String): Flow<PagingData<MoviesDomainModel.ResultDomain>>
}