package suvorov.pokemon.data.repository.page

import suvorov.pokemon.data.database.PokemonDao
import suvorov.pokemon.domain.repository.PageRepository

class PageRepositoryImpl(private val pokemonDao: PokemonDao): PageRepository {

    override fun getPokemonById(id: String) = pokemonDao.getPokemonById(id)

    override fun getEvolutionsByIds(ids: List<String>) = pokemonDao.getEvolutionsByIds(ids)
}