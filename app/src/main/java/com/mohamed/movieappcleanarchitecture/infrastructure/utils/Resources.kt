package com.mohamed.movieappcleanarchitecture.infrastructure.utils

sealed class Resources<out T>(val states: States, val data: T?, val error: Throwable?) {

    class OnSuccess<T>(_data: T?) :Resources<T>(states = States.SUCCESS, data = _data, error = null)

    class OnError<T>(throwable: Throwable?) :Resources<T>(states = States.ERROR, data = null, error = throwable)

    class OnLoading<T>() : Resources<T>(states = States.LOADING, data = null, error = null)

}