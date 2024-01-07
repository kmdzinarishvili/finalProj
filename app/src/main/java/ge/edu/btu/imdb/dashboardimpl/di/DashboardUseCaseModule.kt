package ge.edu.btu.imdb.dashboardimpl.container.di

import ge.edu.btu.imdb.dashboardimpl.domain.usecase.remote.GetMovieUseCase
import ge.edu.btu.imdb.dashboardimpl.container.domain.usecase.remote.SearchMovieUseCase
import org.koin.dsl.module

val dashboardUseCaseModule = module {
    single { GetMovieUseCase(moviesRepository = get()) }
    single { SearchMovieUseCase(repository = get()) }
}