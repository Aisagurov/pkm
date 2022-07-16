package suvorov.pokemon.presentation.common

import suvorov.pokemon.data.database.PokemonEntity

interface OnClickListener {

    fun onItemClick(model: PokemonEntity)

    fun onFavoriteClick(id: String)
}