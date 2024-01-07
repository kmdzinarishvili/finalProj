package ge.edu.btu.imdb.coredatabase.di

import ge.edu.btu.imdb.coredatabase.domain.usecase.AddFavoriteMovieUseCase
import ge.edu.btu.imdb.coredatabase.domain.usecase.CheckIfMovieIsFavoritesUseCase
import ge.edu.btu.imdb.coredatabase.domain.usecase.DeleteFavoriteMovieUseCase
import ge.edu.btu.imdb.coredatabase.domain.usecase.GetAllFavoriteMovieIdsUseCase
import ge.edu.btu.imdb.coredatabase.domain.usecase.GetFavoriteMoviesUseCase
import org.koin.dsl.module

val coreDBUseCaseModule = module {
    single { AddFavoriteMovieUseCase(favoritesRepository = get()) }
    single { DeleteFavoriteMovieUseCase(favoritesRepository = get()) }
    single { GetFavoriteMoviesUseCase(favoritesRepository = get()) }
    single { CheckIfMovieIsFavoritesUseCase(favoritesRepository = get()) }
    single { GetAllFavoriteMovieIdsUseCase(favoritesRepository = get()) }
}