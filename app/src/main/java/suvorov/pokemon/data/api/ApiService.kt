package suvorov.pokemon.data.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("pokemon.json")
    suspend fun getPokemonList(): Response<List<PokemonResponse>>
}