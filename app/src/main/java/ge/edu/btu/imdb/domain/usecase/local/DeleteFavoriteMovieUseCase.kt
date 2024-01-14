package ge.edu.btu.imdb.domain.usecase.local

import ge.edu.btu.imdb.domain.repository.local.FavoritesRepository

class DeleteFavoriteMovieUseCase(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke(movieId: Int) {
        favoritesRepository.deleteFavoriteMovieById(movieId)
    }
}