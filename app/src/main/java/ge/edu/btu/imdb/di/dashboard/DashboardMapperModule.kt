package ge.edu.btu.imdb.di.dashboard

import ge.edu.btu.imdb.domain.mapper.MoviesDTOToDomainMapper
import org.koin.dsl.module

val dashboardMapperModule = module {
    single { MoviesDTOToDomainMapper() }
}