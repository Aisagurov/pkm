package suvorov.pokemon.presentation.di.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import suvorov.pokemon.presentation.ui.viewmodel.FavoritesViewModel
import suvorov.pokemon.presentation.ui.viewmodel.ListViewModel
import suvorov.pokemon.presentation.ui.viewmodel.PagesViewModel

val viewModelModule = module {
    viewModel {
        ListViewModel(get())
    }

    viewModel {
        FavoritesViewModel(get())
    }

    viewModel {
        PagesViewModel(get())
    }
}