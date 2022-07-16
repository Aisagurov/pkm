package suvorov.pokemon.data.repository.list

import suvorov.pokemon.data.api.ApiService
import suvorov.pokemon.data.api.PokemonResponse
import suvorov.pokemon.data.repository.BaseRemoteDataSource
import suvorov.pokemon.domain.common.Result

class ListRemoteDataSourceImpl(private val service: ApiService): BaseRemoteDataSource(), ListRemoteDataSource {

    override suspend fun getPokemonList(): Result<List<PokemonResponse>> {
        return getResult {
            service.getPokemonList()
        }
    }
}