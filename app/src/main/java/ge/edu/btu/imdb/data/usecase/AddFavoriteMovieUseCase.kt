package ge.edu.btu.imdb.data.usecase

import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import ge.edu.btu.imdb.data.repository.FavoritesRepository

class AddFavoriteMovieUseCase(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke(movie: MoviesDomainModel.ResultDomain) {
        favoritesRepository.addFavoriteMovie(movie)
    }
}