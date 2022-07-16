package suvorov.pokemon.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_list")
    fun getPokemonList(): LiveData<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_list WHERE id = :id")
    suspend fun getPokemonFromId(id: String): PokemonEntity?

    @Query("SELECT * FROM pokemon_list WHERE id = :id")
    fun getPokemonById(id: String): LiveData<PokemonEntity>

    @Query("SELECT * FROM pokemon_list WHERE id IN(:evolutionIds)")
    fun getEvolutionsByIds(evolutionIds: List<String>): LiveData<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_list WHERE favorite = 1")
    fun favoritePokemon(): LiveData<List<PokemonEntity>>

    @Query("SELECT id FROM pokemon_list WHERE favorite = 1")
    suspend fun favoriteIds(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<PokemonEntity>)

    @Update
    suspend fun update(pokemon: PokemonEntity): Int
}