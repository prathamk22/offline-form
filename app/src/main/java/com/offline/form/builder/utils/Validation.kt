package com.offline.form.builder.utils

interface Validation<T> {

    fun isValid(item: T): Boolean

}