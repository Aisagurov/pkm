package suvorov.pokemon.data.repository.favorite

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity

interface FavoritesLocalDataSource {

    val favoritePokemon: LiveData<List<PokemonEntity>>

    suspend fun updateFavoriteStatus(id: String): PokemonEntity?
}