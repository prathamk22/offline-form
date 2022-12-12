package com.offline.form.builder.ui.home

import android.text.InputType
import androidx.lifecycle.ViewModel
import com.offline.form.builder.utils.*

class HomeViewModel : ViewModel() {

    val questions: List<Question> by lazy {
        listOf(
            Question(
                id = "Input 1",
                question = "What is your name",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT)
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "Input 2",
                question = "What is my name",
                options = listOf(
                    OptionType.CheckBox(listOf(CheckBoxItems("Option 1"), CheckBoxItems("Option 2")))
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            )
        )
    }



}
