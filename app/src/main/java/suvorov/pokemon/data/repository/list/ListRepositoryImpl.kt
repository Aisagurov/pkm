package suvorov.pokemon.data.repository.list

import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.domain.common.succeed
import suvorov.pokemon.domain.repository.ListRepository
import suvorov.pokemon.util.Constants.UNEXPECTED_ERROR

class ListRepositoryImpl(
    private val localDataSource: ListLocalDataSource,
    private val remoteDataSource: ListRemoteDataSource
    ): ListRepository {

    override val pokemonListFromDatabase = localDataSource.pokemonList

    override suspend fun getPokemonListFromApi() {
        when (val result = remoteDataSource.getPokemonList()) {
            is Result.Success -> {
                if(result.succeed) {
                    val favoriteIds = localDataSource.favoriteIds()
                    val customPokemonList = result.data.let {
                        it.filter { item -> item.id.isNullOrEmpty().not()  }
                        it.map {pokemon ->
                            PokemonEntity(
                                pokemon.id ?: "",
                                pokemon.name,
                                pokemon.imageUrl,
                                pokemon.xDescription,
                                pokemon.yDescription,
                                pokemon.height,
                                pokemon.category,
                                pokemon.weight,
                                pokemon.typeOfPokemon,
                                pokemon.weaknesses,
                                pokemon.evolutions,
                                pokemon.abilities,
                                pokemon.hp,
                                pokemon.attack,
                                pokemon.defense,
                                pokemon.specialAttack,
                                pokemon.specialDefense,
                                pokemon.speed,
                                pokemon.total,
                                pokemon.malePercentage,
                                pokemon.femalePercentage,
                                pokemon.genderless,
                                pokemon.cycles,
                                pokemon.eggGroups,
                                pokemon.evolvedFrom,
                                pokemon.reason,
                                pokemon.baseExp,
                                favoriteIds.contains(pokemon.id)
                            )
                        }
                    }
                    localDataSource.insert(customPokemonList)
                    Result.Success(true)
                }else {
                    Result.Error(UNEXPECTED_ERROR)
                }
            }
            else -> result as Result.Error
        }
    }

    override suspend fun updateFavoriteStatus(id: String): Result<PokemonEntity> {
        val result = localDataSource.updateFavoriteStatus(id)
        return result?.let {
            Result.Success(it)
        } ?: Result.Error(UNEXPECTED_ERROR)
    }
}