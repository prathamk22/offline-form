package com.offline.form.builder.utils

class StringInputValidation(
    private val length: Int = -1
) : Validation<Any> {

    var errorMsg = ""

    override fun isValid(it: Any): Boolean {
        val item = it as String
        if (item.isEmpty()){
            errorMsg = "Text should not be empty"
            return false
        }
        if (length > 0 && (item.length != length)){
            errorMsg = "Text should be of $length only."
            return false
        }
        errorMsg = ""
        return true
    }

    override fun getError() = errorMsg

}