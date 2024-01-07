package ge.edu.btu.imdb.dashboardimpl.container.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ge.edu.btu.imdb.dashboardimpl.data.remote.network.MoviesApiService
import ge.edu.btu.imdb.dashboardimpl.remote.model.dto.MoviesDTOModel
import retrofit2.HttpException

/**
 * A PagingSource class for loading paginated movies data based on a specified category.
 *
 * @param moviesApiService The MoviesApiService instance used for making API calls.
 * @param category The category of movies to load (e.g., "popular", "top_rated", "upcoming").
 */
class MoviesPagingSource(
    private val moviesApiService: MoviesApiService,
    private val category: String,
) : PagingSource<Int, MoviesDTOModel.ResultDTO>() {

    /**
     * Loads the requested page of movies data from the API.
     *
     * @param params The LoadParams object containing information about the requested load.
     * @return A LoadResult containing the loaded data or an error if the load operation fails.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDTOModel.ResultDTO> {
        return try {
            val currentPage = params.key ?: 1
            val response = moviesApiService.getMoviesByCategory(category, currentPage)
            val data = response.body()!!


            val totalPages = response.body()!!.totalPages

            LoadResult.Page(
                data = data.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage == totalPages) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    /**
     * Returns the key that should be used to refresh the currently loaded data.
     *
     * @param state The current state of the PagingSource, containing information about the loaded data.
     * @return The key to be used for refreshing the data, or null if a refresh is not supported.
     */
    override fun getRefreshKey(state: PagingState<Int, MoviesDTOModel.ResultDTO>): Int? {
        return state.anchorPosition
    }
}