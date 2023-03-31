package com.offline.form.builder.utils

class NumberInputValidation(
    private val minimum: Int = 0,
    private val maximum: Int = Int.MAX_VALUE
) : Validation<Any> {

    private var errorMessage: String? = null

    override fun isValid(it: Any): Boolean {
        try {
            val item = it.toString().toFloatOrNull() ?: return false
            if (item <= minimum) {
                errorMessage = "Number should be greater than $minimum"
                return false
            }

            if (item >= maximum) {
                errorMessage = "Number should be smaller than $maximum"
                return false
            }
            errorMessage = null
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    override fun getError() = errorMessage

}