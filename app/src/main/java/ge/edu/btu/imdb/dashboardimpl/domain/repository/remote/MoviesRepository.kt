package ge.edu.btu.imdb.dashboardimpl.container.domain.repository.remote

import androidx.paging.PagingData
import ge.edu.btu.imdb.coredomain.remote.MoviesDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesByCategory(category: String): Flow<PagingData<MoviesDomainModel.ResultDomain>>
    fun searchMovies(query: String): Flow<PagingData<MoviesDomainModel.ResultDomain>>
}