package ge.edu.btu.imdb.coredatabase.domain.usecase

import ge.edu.btu.imdb.coredomain.remote.MoviesDomainModel
import ge.edu.btu.imdb.coredatabase.domain.repository.FavoritesRepository

class AddFavoriteMovieUseCase(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke(movie: MoviesDomainModel.ResultDomain) {
        favoritesRepository.addFavoriteMovie(movie)
    }
}