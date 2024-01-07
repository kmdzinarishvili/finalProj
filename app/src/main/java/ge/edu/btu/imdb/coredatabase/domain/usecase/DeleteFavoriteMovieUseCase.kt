package ge.edu.btu.imdb.coredatabase.domain.usecase

import ge.edu.btu.imdb.coredatabase.domain.repository.FavoritesRepository

class DeleteFavoriteMovieUseCase(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke(movieId: Int) {
        favoritesRepository.deleteFavoriteMovieById(movieId)
    }
}