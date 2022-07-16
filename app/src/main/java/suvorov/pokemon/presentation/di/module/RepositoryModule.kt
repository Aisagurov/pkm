package suvorov.pokemon.presentation.di.module

import org.koin.dsl.module
import suvorov.pokemon.data.repository.favorite.FavoritesLocalDataSourceImpl
import suvorov.pokemon.data.repository.favorite.FavoritesRepositoryImpl
import suvorov.pokemon.data.repository.list.ListLocalDataSourceImpl
import suvorov.pokemon.data.repository.list.ListRemoteDataSourceImpl
import suvorov.pokemon.data.repository.list.ListRepositoryImpl
import suvorov.pokemon.data.repository.page.PageRepositoryImpl
import suvorov.pokemon.data.repository.favorite.FavoritesLocalDataSource
import suvorov.pokemon.domain.repository.FavoritesRepository
import suvorov.pokemon.data.repository.list.ListLocalDataSource
import suvorov.pokemon.data.repository.list.ListRemoteDataSource
import suvorov.pokemon.domain.repository.ListRepository
import suvorov.pokemon.domain.repository.PageRepository

val repositoryModule = module {
    factory {
        val listLocalDataSource: ListLocalDataSource =
            ListLocalDataSourceImpl(get())
        listLocalDataSource
    }

    factory {
        val listRemoteDataSource: ListRemoteDataSource =
            ListRemoteDataSourceImpl(get())
        listRemoteDataSource
    }

    factory {
        val listRepository: ListRepository =
            ListRepositoryImpl(get(), get())
        listRepository
    }

    factory {
        val favoritesLocalDataSource: FavoritesLocalDataSource =
            FavoritesLocalDataSourceImpl(get())
        favoritesLocalDataSource
    }

    factory {
        val favoritesRepository: FavoritesRepository =
            FavoritesRepositoryImpl(get())
        favoritesRepository
    }

    factory {
        val pageRepository: PageRepository =
            PageRepositoryImpl(get())
        pageRepository
    }
}