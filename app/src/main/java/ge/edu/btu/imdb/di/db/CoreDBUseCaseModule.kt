package ge.edu.btu.imdb.di.db

import ge.edu.btu.imdb.domain.usecase.local.AddFavoriteMovieUseCase
import ge.edu.btu.imdb.domain.usecase.local.CheckIfMovieIsFavoritesUseCase
import ge.edu.btu.imdb.domain.usecase.local.DeleteFavoriteMovieUseCase
import ge.edu.btu.imdb.domain.usecase.local.GetAllFavoriteMovieIdsUseCase
import ge.edu.btu.imdb.domain.usecase.local.GetFavoriteMoviesUseCase
import org.koin.dsl.module

val coreDBUseCaseModule = module {
    single { AddFavoriteMovieUseCase(favoritesRepository = get()) }
    single { DeleteFavoriteMovieUseCase(favoritesRepository = get()) }
    single { GetFavoriteMoviesUseCase(favoritesRepository = get()) }
    single { CheckIfMovieIsFavoritesUseCase(favoritesRepository = get()) }
    single { GetAllFavoriteMovieIdsUseCase(favoritesRepository = get()) }
}