package com.offline.form.builder.utils

import android.text.InputType

sealed class OptionType {

    data class InputField(val inputType: Int) : OptionType()

    data class CheckBox(
        val checkboxItems: List<CheckBoxItems>
    ) : OptionType()

}


data class CheckBoxItems(
    val optionTitle: String,
)