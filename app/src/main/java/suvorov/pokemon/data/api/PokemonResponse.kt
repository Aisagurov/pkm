package suvorov.pokemon.data.api

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: String?,
    val name: String?,
    @SerializedName("imageurl") val imageUrl: String?,
    @SerializedName("xdescription") val xDescription: String?,
    @SerializedName("ydescription") val yDescription: String?,
    val height: String?,
    val category: String?,
    val weight: String?,
    @SerializedName("typeofpokemon") val typeOfPokemon: List<String>?,
    val weaknesses: List<String>?,
    val evolutions: List<String>?,
    val abilities: List<String>?,
    val hp: Int?,
    val attack: Int?,
    val defense: Int?,
    @SerializedName("special_attack") val specialAttack: Int?,
    @SerializedName("special_defense") val specialDefense: Int?,
    val speed: Int?,
    val total: Int?,
    @SerializedName("male_percentage") val malePercentage: String?,
    @SerializedName("female_percentage") val femalePercentage: String?,
    val genderless: Int?,
    val cycles: String?,
    @SerializedName("egg_groups") val eggGroups: String?,
    @SerializedName("evolvedfrom") val evolvedFrom: String?,
    val reason: String?,
    @SerializedName("base_exp") val baseExp: String?
)