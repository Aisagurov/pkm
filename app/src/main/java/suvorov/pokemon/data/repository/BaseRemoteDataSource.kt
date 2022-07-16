package suvorov.pokemon.data.repository

import retrofit2.Response
import suvorov.pokemon.domain.common.Result
import suvorov.pokemon.util.Constants

abstract class BaseRemoteDataSource {
    suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            } else if (response.errorBody() != null) {
                return error(response.errorBody().toString())
            }
            return error(Constants.UNEXPECTED_ERROR)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun error(message: String): Result.Error = Result.Error(message)
}