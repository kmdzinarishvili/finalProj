package ge.edu.btu.imdb.domain.usecase.local

import ge.edu.btu.imdb.domain.repository.local.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteMovieIdsUseCase(private val favoritesRepository: FavoritesRepository) {
    fun invoke(): Flow<List<Int>> {
        return favoritesRepository.getAllFavoriteMovieIds()
    }
}