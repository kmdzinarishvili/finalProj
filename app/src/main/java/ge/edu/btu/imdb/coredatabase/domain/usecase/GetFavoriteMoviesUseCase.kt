package ge.edu.btu.imdb.coredatabase.domain.usecase

import ge.edu.btu.imdb.coredomain.remote.MoviesDomainModel
import ge.edu.btu.imdb.coredatabase.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(private val favoritesRepository: FavoritesRepository) {

    operator fun invoke(): Flow<List<MoviesDomainModel.ResultDomain>> {
        return favoritesRepository.getAllFavoriteMovies()
    }
}