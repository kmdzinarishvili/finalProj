package ge.edu.btu.imdb.dashboardimpl.remote.model.dto

import com.squareup.moshi.Json

data class MoviesDTOModel(
    val page: Int,
    val results: List<ResultDTO>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
) {
    data class ResultDTO(
        val adult: Boolean?,
        @Json(name = "backdrop_path")
        val backdropPath: String?,
        @Json(name = "genre_ids")
        val genreIds: List<Int>?,
        val id: Int,
        @Json(name = "original_language")
        val originalLanguage: String?,
        @Json(name = "original_title")
        val originalTitle: String?,
        val overview: String?,
        val popularity: Double?,
        @Json(name = "poster_path")
        val posterPath: String?,
        @Json(name = "release_date")
        val releaseDate: String?,
        val title: String?,
        val video: Boolean?,
        @Json(name = "vote_average")
        val voteAverage: Double?,
        @Json(name = "vote_count")
        val voteCount: Int?
    )
}