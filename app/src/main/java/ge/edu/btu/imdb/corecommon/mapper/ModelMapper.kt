package ge.edu.btu.imdb.corecommon.mapper

interface ModelMapper<in ModelA, out ModelB> {
    operator fun invoke(model: ModelA): ModelB
}
