package ge.edu.btu.imdb.domain.usecase.remote

import androidx.paging.PagingData
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.domain.repository.remote.MoviesRepository
import kotlinx.coroutines.flow.Flow


class GetMovieUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke(params: String?): Flow<PagingData<MoviesDomainModel.ResultDomain>> {
        return moviesRepository.getMoviesByCategory(params!!)
    }
}