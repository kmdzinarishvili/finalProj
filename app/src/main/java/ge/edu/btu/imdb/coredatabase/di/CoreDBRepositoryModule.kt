package ge.edu.btu.imdb.coredatabase.di

import ge.edu.btu.imdb.coredatabase.data.repository.FavoritesRepositoryImpl
import ge.edu.btu.imdb.coredatabase.domain.repository.FavoritesRepository
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