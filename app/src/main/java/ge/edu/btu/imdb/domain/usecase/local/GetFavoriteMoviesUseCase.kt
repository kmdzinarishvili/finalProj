package ge.edu.btu.imdb.domain.usecase.local

import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.domain.repository.local.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(private val favoritesRepository: FavoritesRepository) {

    operator fun invoke(): Flow<List<MoviesDomainModel.ResultDomain>> {
        return favoritesRepository.getAllFavoriteMovies()
    }
}