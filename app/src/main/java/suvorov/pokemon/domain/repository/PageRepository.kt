package suvorov.pokemon.domain.repository

import androidx.lifecycle.LiveData
import suvorov.pokemon.data.database.PokemonEntity

interface PageRepository {

    fun getPokemonById(id: String): LiveData<PokemonEntity>

    fun getEvolutionsByIds(ids: List<String>): LiveData<List<PokemonEntity>>
}