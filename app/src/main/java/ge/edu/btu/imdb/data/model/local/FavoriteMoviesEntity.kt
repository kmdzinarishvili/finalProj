package ge.edu.btu.imdb.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMoviesEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<String>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)