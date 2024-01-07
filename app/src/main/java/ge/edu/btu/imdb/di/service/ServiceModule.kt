package ge.edu.btu.imdb.di.service

import ge.edu.btu.imdb.data.network.MoviesApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single {
       get<Retrofit>().create(MoviesApiService::class.java)
   }
}
