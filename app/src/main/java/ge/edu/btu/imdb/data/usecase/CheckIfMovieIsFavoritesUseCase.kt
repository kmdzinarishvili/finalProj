package ge.edu.btu.imdb.data.usecase

import ge.edu.btu.imdb.data.repository.FavoritesRepository

class CheckIfMovieIsFavoritesUseCase(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke(movieId: Int): Boolean {
        return favoritesRepository.isMovieFavorite(movieId)
    }
}