package com.corps.werewolfvoices.domain.model

sealed interface DataResult<out T> {
    data class Success<T>(val value: T) : DataResult<T>
    data class Error(val message: String, val throwable: Throwable? = null) : DataResult<Nothing>
    data object Loading : DataResult<Nothing>
}
