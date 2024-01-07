package ge.edu.btu.imdb.data.model.remote.dto

data class MoviesGenresDTO(
    val genres: List<Genre>
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}