package ge.edu.btu.imdb.di.db

import ge.edu.btu.imdb.data.repository.local.FavoritesRepositoryImpl
import ge.edu.btu.imdb.domain.repository.local.FavoritesRepository
import org.koin.dsl.module

val coreDBRepositoryModule = module {
    single<FavoritesRepository> {
        FavoritesRepositoryImpl(
            favoriteMoviesDao = get(),
            domainToEntityMapper = get(),
            entityToDomainMapper = get()
        )
    }
}