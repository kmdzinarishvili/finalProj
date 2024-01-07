package ge.edu.btu.imdb.coredomain.usecase

abstract class BaseUseCase<in Params, out T> {
    abstract suspend operator fun invoke(params: Params? = null): T
}
