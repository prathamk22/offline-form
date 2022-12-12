package com.offline.form.builder.utils

class NumberInputValidation(
    private val minimum: Int,
    private val maximum: Int
) : Validation<Any> {

    private var errorMessage = ""

    override fun isValid(it: Any): Boolean {
        try {
            val item = it as Long
            if (item <= minimum) {
                errorMessage = "Number should be greater than ${minimum}"
                return false
            }

            if (item >= maximum) {
                errorMessage = "Number should be smaller than ${maximum}"
                return false
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun getError() = errorMessage
}