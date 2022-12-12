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
                id = "A1",
                question = "Name of the Respondent",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter your name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A2",
                question = " District Name ",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter District Name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A3",
                question = "Block Name",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter name of Block")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A4",
                question = "Agri-Camp Name",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Agri-Camp Name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A5",
                question = "Village Name",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Village Name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A6",
                question = "Household Serial No.",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Household Serial No.")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A7",
                question = "Consent from the Respondent",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("1", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "A8",
                question = " Age of Respondent",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Age of Respondent")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A9",
                question = "Gender of the Respondent",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Male"),
                            CheckBoxItems("2", "Female")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "A10",
                question = "Mobile number of respondent / House Hold member",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_PHONE, "Enter Mobile number of respondent / House Hold member")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A11",
                question = "Are you Head of the Family",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            //ADD  A.12 Name of the family Head (if response of A.10 is (2))
            Question(
                id = "A13",
                question = "Gender of the head of Household",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Male"),
                            CheckBoxItems("2", "Female")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "A14",
                question = "Relationship of respondent to Head of HH",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", " Head of Household"),
                            CheckBoxItems("2", "Spouse"),
                            CheckBoxItems("3", "Child (own/step)"),
                            CheckBoxItems("4", "Parent /Parent in law"),
                            CheckBoxItems("5", "Brother/sister"),
                            CheckBoxItems("6", "Other relatives"),
                            CheckBoxItems("7", "Unrelated"),

                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "A15",
                question = "Marital status of respondent",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Single (never married)"),
                            CheckBoxItems("2", "Monogamously Married"),
                            CheckBoxItems("3", "Polygamously Married"),
                            CheckBoxItems("4", "Divorced"),
                            CheckBoxItems("5", "Widowed"),
                            CheckBoxItems("6", "Separated"),
                            CheckBoxItems("7", "Cohabiting"),

                            )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "A16",
                question = "Highest Education level of respondent",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "No Formal Education"),
                            CheckBoxItems("2", "Less than Grade 5"),
                            CheckBoxItems("3", "Less than grade 10"),
                            CheckBoxItems("4", "Less than Grade 12"),
                            CheckBoxItems("5", "College Student"),
                            CheckBoxItems("6", "University Undergraduate Student"),
                            CheckBoxItems("7", "Tertiary Certificate;Diploma"),
                            CheckBoxItems("8", "Bachelors Degree"),
                            CheckBoxItems("9", "Masters Degree and Above."),

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
