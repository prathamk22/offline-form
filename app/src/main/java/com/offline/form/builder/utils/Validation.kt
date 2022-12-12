package com.offline.form.builder.utils

interface Validation<T> {

    fun isValid(it: T): Boolean

    fun getError(): String?

}