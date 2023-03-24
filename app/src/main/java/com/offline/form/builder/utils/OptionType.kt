package com.offline.form.builder.utils

import android.view.View

sealed class OptionType {

    data class InputField(val inputType: Int, val hint: String) : OptionType()

    data class CheckBox(
        val checkboxItems: List<CheckBoxItems>,
        val isOtherOptionAllowed: Boolean = false
    ) : OptionType()

    data class Button(
        val buttonText: String,
        val buttonAction: ButtonAction
    ) : OptionType()

}


interface ButtonAction {
    fun doAction(view: View, question: Question)
}

data class CheckBoxItems(
    val id: String,
    val optionTitle: String,
    val isTextOnly: Boolean = false,
)