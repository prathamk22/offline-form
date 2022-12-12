package com.offline.form.builder.ui.home

import android.text.InputType
import androidx.lifecycle.ViewModel
import com.offline.form.builder.utils.OptionType
import com.offline.form.builder.utils.Question
import com.offline.form.builder.utils.StringInputValidation

class HomeViewModel : ViewModel() {

    val questions: List<Question> by lazy {
        listOf(
            Question(
                id = "",
                question = "",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT)
                ),
                validate = StringInputValidation()
            )
        )
    }



}
