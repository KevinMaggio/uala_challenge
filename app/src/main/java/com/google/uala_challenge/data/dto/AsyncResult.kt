package com.google.uala_challenge.data.dto

sealed class AsyncResult<R, E> {

    data class Success<R, E>(val value: R) : AsyncResult<R, E>()
    data class Failure<R, E>(val error: E) : AsyncResult<R, E>()

    fun isSuccess(): Boolean = this is Success
    fun isFailure(): Boolean = this is Failure

    fun asSuccess(): Success<R, E>? = this as? Success
    fun asFailure(): Failure<R, E>? = this as? Failure

    fun getValueOrNull(): R? {
        return (this as? Success)?.value
    }

    fun getErrorOrNull(): E? {
        return (this as? Failure)?.error
    }
}
