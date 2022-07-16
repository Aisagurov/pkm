package suvorov.pokemon.domain.repository

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.domain.common.Result

interface ListRepository {

    val pokemonListFromDatabase: LiveData<List<PokemonEntity>>

    suspend fun getPokemonListFromApi()

    suspend fun updateFavoriteStatus(id: String): Result<PokemonEntity>
}