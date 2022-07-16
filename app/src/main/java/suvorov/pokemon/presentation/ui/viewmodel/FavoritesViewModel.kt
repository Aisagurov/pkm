package suvorov.pokemon.presentation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.domain.repository.FavoritesRepository

class FavoritesViewModel(private val repository: FavoritesRepository): ViewModel() {

    private val toastError = MutableLiveData<String>()

    private val favoriteStock = MutableLiveData<PokemonEntity?>()

    val favoritePokemon = repository.favoritePokemon

    fun updateFavoriteStatus(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.updateFavoriteStatus(id)) {
                is Result.Success -> favoriteStock.postValue(result.data)
                is Result.Error -> toastError.postValue(result.message)
            }
        }
    }
}