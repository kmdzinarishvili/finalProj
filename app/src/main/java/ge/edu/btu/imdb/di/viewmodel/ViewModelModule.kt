package ge.edu.btu.imdb.di.viewmodel

import ge.edu.btu.imdb.splash.presentation.viewmodel.SplashViewModel
import ge.edu.btu.imdb.dashboardimpl.feature.favorites.presentation.viewmodel.FavoritesViewModel
import ge.edu.btu.imdb.dashboardimpl.feature.home.presentation.viewmodel.HomeViewModel
import ge.edu.btu.imdb.detailimpl.viewmodel.DetailViewModel
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