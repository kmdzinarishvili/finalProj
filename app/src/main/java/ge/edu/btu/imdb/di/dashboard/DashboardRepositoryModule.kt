package ge.edu.btu.imdb.di.dashboard

import ge.edu.btu.imdb.data.repository.remote.MoviesRepositoryImpl
import ge.edu.btu.imdb.domain.repository.remote.MoviesRepository
import org.koin.dsl.module

val dashboardRepositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(
            moviesApiService = get(), mapper =get()
        )
    }
}