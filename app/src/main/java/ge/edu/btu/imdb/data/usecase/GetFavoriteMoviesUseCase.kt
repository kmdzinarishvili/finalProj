package ge.edu.btu.imdb.data.usecase

import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import ge.edu.btu.imdb.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(private val favoritesRepository: FavoritesRepository) {

    operator fun invoke(): Flow<List<MoviesDomainModel.ResultDomain>> {
        return favoritesRepository.getAllFavoriteMovies()
    }
}