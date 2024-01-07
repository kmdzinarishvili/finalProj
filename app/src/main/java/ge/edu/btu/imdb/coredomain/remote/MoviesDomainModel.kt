package ge.edu.btu.imdb.coredomain.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDomainModel(
    val page: Int,
    val results: List<ResultDomain>,
    val totalPages: Int,
    val totalResults: Int
) : Parcelable {
    @Parcelize
    data class ResultDomain(
        val adult: Boolean?,
        val backdropPath: String?,
        val genreIds: List<String>?,
        val id: Int,
        val originalLanguage: String?,
        val originalTitle: String?,
        val overview: String?,
        val popularity: Double?,
        val posterPath: String?,
        val releaseDate: String?,
        val title: String?,
        val video: Boolean?,
        val voteAverage: Double?,
        val voteCount: Int?,
    ) : Parcelable {
        var isFavorite: Boolean = false
    }
}
