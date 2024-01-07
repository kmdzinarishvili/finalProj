package ge.edu.btu.imdb.dashboardimpl.container.di

import ge.edu.btu.imdb.dashboardimpl.domain.mapper.MoviesDTOToDomainMapper
import org.koin.dsl.module

val dashboardMapperModule = module {
    single { MoviesDTOToDomainMapper() }
}