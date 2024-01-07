package ge.edu.btu.imdb.data.mapper

import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import ge.edu.btu.imdb.data.model.FavoriteMoviesEntity

class EntityToDomainMapper : ModelMapper<FavoriteMoviesEntity, MoviesDomainModel.ResultDomain> {
    override operator fun invoke(model: FavoriteMoviesEntity): MoviesDomainModel.ResultDomain {
        with(model){
        return MoviesDomainModel.ResultDomain(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath,
            adult = adult,
            backdropPath = backdropPath,
            genreIds = genreIds,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            popularity = popularity,
            releaseDate = releaseDate,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }}
}