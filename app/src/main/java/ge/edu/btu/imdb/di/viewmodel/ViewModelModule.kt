package ge.edu.btu.imdb.di.viewmodel

import ge.edu.btu.imdb.presentation.viewmodel.SplashViewModel
import ge.edu.btu.imdb.presentation.viewmodel.FavoritesViewModel
import ge.edu.btu.imdb.presentation.viewmodel.HomeViewModel
import ge.edu.btu.imdb.presentation.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(navigationApi = get()) }
    viewModel {
        DetailViewModel(
            addFavoriteMovieUseCase = get(),
            checkIfMovieIsFavoritesUseCase = get(),
            deleteFavoriteMovieUseCase = get(),
            navigationApi = get()
        )
    }
    viewModel {
        HomeViewModel(
            navigationApi = get(),
            getMovieUseCase = get(),
            searchMovieUseCase = get(),
            addFavoriteMovieUseCase = get(),
            checkIfMovieIsFavoritesUseCase = get(),
            deleteFavoriteMovieUseCase = get(),
            getAllFavoriteMovieIdsUseCase = get()
        )
    }
    viewModel {
        FavoritesViewModel(
            navigationApi = get(),
            getFavoriteMoviesUseCase = get(),
            deleteFavoriteMovieUseCase = get()
        )
    }
}