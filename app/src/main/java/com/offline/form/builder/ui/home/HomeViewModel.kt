package com.offline.form.builder.ui.home

import android.text.InputType
import android.util.Log
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
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("ID1", "Option 1"),
                            CheckBoxItems("ID2", "Option 2")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            )
        )
    }

    private val answers = mutableMapOf<String, String>()


    fun valueEntered(key: String, value: String) {
        Log.e("TAG", "valueEntered: Value is here $key $value")
        answers[key] = value
    }

    fun getAnsIfAvailable(key: String): String? = answers[key]


}
