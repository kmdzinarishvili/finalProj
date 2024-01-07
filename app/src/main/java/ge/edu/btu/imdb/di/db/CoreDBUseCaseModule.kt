package ge.edu.btu.imdb.di.db

import ge.edu.btu.imdb.data.usecase.AddFavoriteMovieUseCase
import ge.edu.btu.imdb.data.usecase.CheckIfMovieIsFavoritesUseCase
import ge.edu.btu.imdb.data.usecase.DeleteFavoriteMovieUseCase
import ge.edu.btu.imdb.data.usecase.GetAllFavoriteMovieIdsUseCase
import ge.edu.btu.imdb.data.usecase.GetFavoriteMoviesUseCase
import org.koin.dsl.module

val coreDBUseCaseModule = module {
    single { AddFavoriteMovieUseCase(favoritesRepository = get()) }
    single { DeleteFavoriteMovieUseCase(favoritesRepository = get()) }
    single { GetFavoriteMoviesUseCase(favoritesRepository = get()) }
    single { CheckIfMovieIsFavoritesUseCase(favoritesRepository = get()) }
    single { GetAllFavoriteMovieIdsUseCase(favoritesRepository = get()) }
}