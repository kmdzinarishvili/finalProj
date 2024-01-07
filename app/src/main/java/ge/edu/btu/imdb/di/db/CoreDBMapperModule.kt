package ge.edu.btu.imdb.di.db

import ge.edu.btu.imdb.data.mapper.DomainToEntityMapper
import ge.edu.btu.imdb.data.mapper.EntityToDomainMapper
import org.koin.dsl.module

val coreDBMapperModule = module {
    single { DomainToEntityMapper() }
    single { EntityToDomainMapper() }
}