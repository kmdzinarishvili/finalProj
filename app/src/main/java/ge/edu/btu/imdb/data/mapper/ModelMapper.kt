package ge.edu.btu.imdb.data.mapper

interface ModelMapper<in ModelA, out ModelB> {
    operator fun invoke(model: ModelA): ModelB
}
