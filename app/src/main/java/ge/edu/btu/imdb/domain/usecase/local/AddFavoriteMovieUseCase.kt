package ge.edu.btu.imdb.domain.usecase.local

import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.domain.repository.local.FavoritesRepository

class AddFavoriteMovieUseCase(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke(movie: MoviesDomainModel.ResultDomain) {
        favoritesRepository.addFavoriteMovie(movie)
    }
}