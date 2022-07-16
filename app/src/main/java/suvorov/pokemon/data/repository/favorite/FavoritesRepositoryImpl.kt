package suvorov.pokemon.data.repository.favorite

import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.domain.repository.FavoritesRepository
import suvorov.pokemon.util.Constants.UNEXPECTED_ERROR

class FavoritesRepositoryImpl(private val localDataSource: FavoritesLocalDataSource): FavoritesRepository {

    override val favoritePokemon = localDataSource.favoritePokemon

    override suspend fun updateFavoriteStatus(id: String): Result<PokemonEntity> {
        val result = localDataSource.updateFavoriteStatus(id)
        return result?.let {
            Result.Success(it)
        } ?: Result.Error(UNEXPECTED_ERROR)
    }
}