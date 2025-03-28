package br.com.las.core.domain.result

sealed class RepositoryResult<out T> {
    data class Success<out T>(val data: T) : RepositoryResult<T>()
    data class Failure(val error: RepositoryError) : RepositoryResult<Nothing>()
}

data class RepositoryError(val code: Int? = null, val message: String = "Unknown Error")