package ge.edu.btu.imdb.corenetwork.result_handler

sealed class Result<T>(val isLoading: Boolean) {
    class Success<T>(val response: T) : Result<T>(false)
    class Error<T>(val errorResponse: String) : Result<T>(false)
    class IsLoading<T>() : Result<T>(true)
}