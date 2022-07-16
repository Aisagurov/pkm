package suvorov.pokemon.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import suvorov.pokemon.data.database.PokemonEntity
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.domain.repository.ListRepository

class ListViewModel(private val repository: ListRepository): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val toastError = MutableLiveData<String>()

    private val favoriteStock = MutableLiveData<PokemonEntity?>()

    val pokemonListFromDatabase = repository.pokemonListFromDatabase

    fun isListEmpty(): Boolean {
        return pokemonListFromDatabase.value?.isEmpty() ?: true
    }

    fun getPokemonFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            repository.getPokemonListFromApi()
            _isLoading.postValue(false)
        }
    }

    fun updateFavoriteStatus(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.updateFavoriteStatus(id)) {
                is Result.Success -> favoriteStock.postValue(result.data)
                is Result.Error -> toastError.postValue(result.message)
            }
        }
    }
}