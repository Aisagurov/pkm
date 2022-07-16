package suvorov.pokemon.presentation.di.module

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import suvorov.pokemon.data.api.ApiService
import suvorov.pokemon.util.Constants.BASE_URL

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create<ApiService>()
    }
}