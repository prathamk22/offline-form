package com.offline.form.builder.utils

class CheckboxInputValidation : Validation<Any> {

    override fun isValid(it: Any): Boolean {
        return true
    }

    override fun getError(): String {
        return ""
    }
}