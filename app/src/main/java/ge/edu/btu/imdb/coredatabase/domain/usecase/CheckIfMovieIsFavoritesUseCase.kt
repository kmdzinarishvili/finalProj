package ge.edu.btu.imdb.coredatabase.domain.usecase

import ge.edu.btu.imdb.coredatabase.domain.repository.FavoritesRepository

class CheckIfMovieIsFavoritesUseCase(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke(movieId: Int): Boolean {
        return favoritesRepository.isMovieFavorite(movieId)
    }
}