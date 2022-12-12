package com.offline.form.builder.utils

sealed class OptionType {

    data class InputField(val inputType: Int, val hint: String) : OptionType()

    data class CheckBox(
        val checkboxItems: List<CheckBoxItems>,
        val isOtherOptionAllowed: Boolean = false
    ) : OptionType()

}


data class CheckBoxItems(
    val id: String,
    val optionTitle: String,
)