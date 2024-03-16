package com.example.cache_mvvm_xml.base

sealed class Result<T>{
 open fun get() : T? = null
}

data class Success<T>(val data : T) : Result<T>() {
    override fun get(): T? = data
}

data class Error<T>(val throwable: Throwable, val message : String, val dbData: T? = null) : Result<T>()


