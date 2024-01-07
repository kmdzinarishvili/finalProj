package ge.edu.btu.imdb.coredatabase.domain.mapper

import ge.edu.btu.imdb.corecommon.mapper.ModelMapper
import ge.edu.btu.imdb.coredomain.remote.MoviesDomainModel
import ge.edu.btu.imdb.coredatabase.data.model.FavoriteMoviesEntity

class DomainToEntityMapper : ModelMapper<MoviesDomainModel.ResultDomain, FavoriteMoviesEntity> {
    override operator fun invoke(model: MoviesDomainModel.ResultDomain): FavoriteMoviesEntity {
        with(model) {
            return FavoriteMoviesEntity(
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
        }
    }
}