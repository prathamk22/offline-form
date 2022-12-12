package com.offline.form.builder.ui.home

import android.text.InputType
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.offline.form.builder.data.db.AnswerEntity
import com.offline.form.builder.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: HomeRepository = HomeRepository()
) : ViewModel() {

    val questions: List<Question> by lazy {
        listOf(
            Question(
                id = "Input 1",
                question = "What is your name",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter your name")
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

    val isEnabled = MutableLiveData(false)

    fun valueEntered(key: String, value: String) {
        Log.e("TAG", "valueEntered: Value is here $key $value")
        answers[key] = value
        checkAndUpdateButton()
    }

    private fun checkAndUpdateButton() {
        viewModelScope.launch(Dispatchers.Default) {
            questions.forEach {
                if (!it.isOptional && answers[it.id].isNullOrEmpty()) {
                    isEnabled.postValue(false)
                    return@launch
                }
            }
            isEnabled.postValue(true)
        }
    }

    fun getAnsIfAvailable(key: String): String? = answers[key]

    fun clearValue(key: String) {
        answers.remove(key)
        checkAndUpdateButton()
    }

    fun insertData() {
        val answerEntity = AnswerEntity(
            nameOfRespondent = "",
            data = Gson().toJson(answers.toMap()),
            createdAt = System.currentTimeMillis()
        )
        viewModelScope.launch {
            repo.insertData(answerEntity)
        }
    }

}
