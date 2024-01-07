package ge.edu.btu.imdb.dashboardimpl.container.remote.model.dto

data class MoviesGenresDTO(
    val genres: List<Genre>
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}