package suvorov.pokemon.data.repository.list

import suvorov.pokemon.data.api.PokemonResponse
import suvorov.pokemon.domain.common.Result

interface ListRemoteDataSource {
    suspend fun getPokemonList(): Result<List<PokemonResponse>>
}