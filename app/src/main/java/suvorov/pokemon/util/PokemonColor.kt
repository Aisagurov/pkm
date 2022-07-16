package suvorov.pokemon.util

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import suvorov.pokemon.R
import java.util.*

class PokemonColor(var context: Context) {
    @ColorInt
    fun getPokemonColor(typeOfPokemon: List<String>?): Int {
        val type = typeOfPokemon?.getOrNull(0)
        val color = when (type?.lowercase(Locale.ROOT)) {
            "grass", "bug" -> R.color.teal_200
            "fire" -> R.color.light_orange
            "water" -> R.color.light_blue
            "normal", "flying" -> R.color.turquoise
            "poison" -> R.color.purple_200
            "electric", "psychic" -> R.color.light_yellow
            "ground", "fighting", "rock" -> R.color.light_brown
            "fairy" -> R.color.light_pink
            "ghost" -> R.color.purple_500
            "dark" -> R.color.light_dark
            else -> R.color.turquoise
        }
        return convertColor(color)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}