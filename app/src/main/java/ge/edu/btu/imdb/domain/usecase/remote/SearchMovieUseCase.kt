package ge.edu.btu.imdb.dashboardimpl.container.domain.usecase.remote

import androidx.paging.PagingData
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.domain.repository.remote.MoviesRepository
import kotlinx.coroutines.flow.Flow

class SearchMovieUseCase(private val repository: MoviesRepository) {

    fun searchMovies(query: String): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        return repository.searchMovies(query)
    }
}
