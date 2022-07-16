package suvorov.pokemon.data.repository.list

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity

interface ListLocalDataSource {

    val pokemonList: LiveData<List<PokemonEntity>>

    suspend fun insert(list: List<PokemonEntity>)

    suspend fun favoriteIds(): List<String>

    suspend fun updateFavoriteStatus(id: String): PokemonEntity?
}