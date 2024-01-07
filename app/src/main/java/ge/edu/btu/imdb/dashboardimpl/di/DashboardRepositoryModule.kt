package ge.edu.btu.imdb.dashboardimpl.container.di

import ge.edu.btu.imdb.dashboardimpl.data.repository.remote.MoviesRepositoryImpl
import ge.edu.btu.imdb.dashboardimpl.container.domain.repository.remote.MoviesRepository
import org.koin.dsl.module

val dashboardRepositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            moviesApiService = get(), mapper =get()
        )
    }
}