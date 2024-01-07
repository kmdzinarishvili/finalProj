package ge.edu.btu.imdb.data.mapper

import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import ge.edu.btu.imdb.dashboardimpl.remote.model.dto.MoviesDTOModel

class MoviesDTOToDomainMapper {
    fun mapToDomainModel(model: MoviesDTOModel.ResultDTO, genreMap: Map<Int, String>): MoviesDomainModel.ResultDomain {
        with(model) {
            return MoviesDomainModel.ResultDomain(
                adult = adult,
                backdropPath = backdropPath,
                genreIds = genreIds?.mapNotNull { genreId -> genreMap[genreId] },
                id = id,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }
    }
}

