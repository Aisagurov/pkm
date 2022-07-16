package suvorov.pokemon.presentation.di.component

import suvorov.pokemon.presentation.di.module.*

val appComponent = listOf(
    databaseModule,
    networkModule,
    repositoryModule,
    viewModelModule
)