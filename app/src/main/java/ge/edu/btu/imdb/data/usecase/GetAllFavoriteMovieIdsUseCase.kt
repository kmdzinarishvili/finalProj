package ge.edu.btu.imdb.data.usecase

import ge.edu.btu.imdb.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteMovieIdsUseCase(private val favoritesRepository: FavoritesRepository) {
    fun invoke(): Flow<List<Int>> {
        return favoritesRepository.getAllFavoriteMovieIds()
    }
}