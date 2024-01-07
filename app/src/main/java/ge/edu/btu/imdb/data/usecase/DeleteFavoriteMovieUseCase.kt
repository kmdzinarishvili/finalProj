package ge.edu.btu.imdb.data.usecase

import ge.edu.btu.imdb.data.repository.FavoritesRepository

class DeleteFavoriteMovieUseCase(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke(movieId: Int) {
        favoritesRepository.deleteFavoriteMovieById(movieId)
    }
}