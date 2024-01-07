package ge.edu.btu.imdb.coredatabase.di

import ge.edu.btu.imdb.coredatabase.domain.mapper.DomainToEntityMapper
import ge.edu.btu.imdb.coredatabase.domain.mapper.EntityToDomainMapper
import org.koin.dsl.module

val coreDBMapperModule = module {
    single { DomainToEntityMapper() }
    single { EntityToDomainMapper() }
}