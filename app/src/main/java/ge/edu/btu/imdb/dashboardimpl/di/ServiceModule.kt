package ge.edu.btu.imdb.dashboardimpl.container.di

import ge.edu.btu.imdb.dashboardimpl.data.remote.network.MoviesApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single {
       get<Retrofit>().create(MoviesApiService::class.java)
   }
}
