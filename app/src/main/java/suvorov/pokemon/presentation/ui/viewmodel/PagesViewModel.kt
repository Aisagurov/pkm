package suvorov.pokemon.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import suvorov.pokemon.domain.repository.PageRepository

class PagesViewModel(private val repository: PageRepository): ViewModel() {

    fun getPokemonById(id: String) = repository.getPokemonById(id)

    fun getEvolutionsByIds(ids: List<String>) = repository.getEvolutionsByIds(ids)
}