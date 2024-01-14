package ge.edu.btu.imdb.di.viewmodel

import ge.edu.btu.imdb.presentation.splash.vm.SplashViewModel
import ge.edu.btu.imdb.presentation.favorites.vm.FavoritesViewModel
import ge.edu.btu.imdb.presentation.home.vm.HomeViewModel
import ge.edu.btu.imdb.presentation.details.vm.DetailViewModel
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