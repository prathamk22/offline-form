package com.offline.form.builder.utils

class StringInputValidation : Validation<Any> {

    var errorMsg = ""

    override fun isValid(it: Any): Boolean {
        val item = it as String
        if (item.isEmpty()){
            errorMsg = "Text should not be empty"
            return false
        }
        errorMsg = ""
        return true
    }

    override fun getError() = errorMsg

}