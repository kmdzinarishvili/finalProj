package ge.edu.btu.imdb.data.network

import ge.edu.btu.imdb.data.model.remote.dto.MoviesGenresDTO
import ge.edu.btu.imdb.dashboardimpl.remote.model.dto.MoviesDTOModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("{category}")
    suspend fun getMoviesByCategory(
        @Path("category") category: String,
        @Query("page") page: Int
    ): Response<MoviesDTOModel>

    @GET("genre/movie/list")
    suspend fun getGenres(): Response<MoviesGenresDTO>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<MoviesDTOModel>

}