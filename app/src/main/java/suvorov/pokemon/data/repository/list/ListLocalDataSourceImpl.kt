package suvorov.pokemon.data.repository.list

import suvorov.pokemon.data.database.PokemonDao
import suvorov.pokemon.data.database.PokemonEntity

class ListLocalDataSourceImpl(private val pokemonDao: PokemonDao): ListLocalDataSource {

    override val pokemonList = pokemonDao.getPokemonList()

    override suspend fun insert(list: List<PokemonEntity>) {
        if (list.isNotEmpty()) {
            pokemonDao.insert(list)
        }
    }

    override suspend fun favoriteIds() = pokemonDao.favoriteIds()

    override suspend fun updateFavoriteStatus(id: String): PokemonEntity? {
        val updatePokemon = pokemonDao.getPokemonFromId(id)
        updatePokemon?.let {
            val pokemon = PokemonEntity(
                it.id,
                it.name,
                it.imageUrl,
                it.xDescription,
                it.yDescription,
                it.height,
                it.category,
                it.weight,
                it.typeOfPokemon,
                it.weaknesses,
                it.evolutions,
                it.abilities,
                it.hp,
                it.attack,
                it.defense,
                it.specialAttack,
                it.specialDefense,
                it.speed,
                it.total,
                it.malePercentage,
                it.femalePercentage,
                it.genderless,
                it.cycles,
                it.eggGroups,
                it.evolvedFrom,
                it.reason,
                it.baseExp,
                it.favorite.not()
            )
            if (pokemonDao.update(pokemon) > 0) {
                return pokemon
            }
        }
        return null
    }
}