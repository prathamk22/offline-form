package com.offline.form.builder.ui.home

import android.text.InputType
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Household Serial No.")
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
            ),
            Question(
                id = "B1",
                question = "How many people are in this household",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter no. of people")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            //B2-B11 Not Written

            Question(
                id = "B12",
                question = "What is the most common health related issue/disease in your area?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter the most common health related issue/disease in the area")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "B13",
                question = "Is the family receiving Government support for medical expenses?",
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
            Question(
                id = "B14",
                question = "Is the family receiving Government support for educational expenses for children in the family??",
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
            Question(
                id = "C1",
                question = "What is the main source of drinking water for members of your household?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "PIPED WATER"),
                            CheckBoxItems("2", "Unprotected well"),
                            CheckBoxItems("3", "PROTECTED WELL"),
                            CheckBoxItems("4", "BOREHOLE"),
                            CheckBoxItems("5", "RIVER, DAM OR STREAM"),
                            CheckBoxItems("6", "OTHER"),
                            )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "C2",
                question = "What kind of toilet facility does your household use?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Bush"),
                            CheckBoxItems("2", "Traditional Pit Latrine"),
                            CheckBoxItems("3", "Improved Pit Latrine (Vip)"),
                            CheckBoxItems("4", "Water- Borne Toilet"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "C2(a)",
                question = "How do you dispose of domestic waste?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Rubbish Pit"),
                            CheckBoxItems("2", "Burnt"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "C3",
                question = "Do you produce enough food to the last year?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),


            //C3(a) and C3(b) Not Written

            Question(
                id = "C4(a)",
                question = "What energy source does your household rely for cooking and lighting? 1.=,  2=, 3=, 4=,  5=, 6=, 7= , 8.= (Specify) ",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Electricity"),
                            CheckBoxItems("2", "LPG/Natural Gas"),
                            CheckBoxItems("3", "Biogas"),
                            CheckBoxItems("4", "Kerosene"),
                            CheckBoxItems("5", "Charcoal"),
                            CheckBoxItems("6", "Firewood"),
                            CheckBoxItems("7", "Dung"),
                            CheckBoxItems("8", "Other"),
                            )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "C4(b)",
                question = "Is your household involved in crop production?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "C4(c)",
                question = "What does your household use to cultivate your farmland?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Hand Tool (HOE/SPADE)"),
                            CheckBoxItems("2", "Animal-Drawn Plow"),
                            CheckBoxItems("3", "Tractor-Drawn Plow"),
                            CheckBoxItems("4", "BOREHOLE"),
                            CheckBoxItems("5", "Other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            //C5 not written
            //Value of household assets not written

            Question(
                id = "D1",
                question = "Annual Household Income (in Kwacha)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Household Income (in Kwacha)")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D2",
                question = "Number of earning household members",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Number of earning household members")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D3",
                question = "Income earned by Women household members (in Kwacha)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Income earned by Women household members (in Kwacha)")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D41",
                question = "Annual Income of the Household From Farming / Agriculture",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Farming / Agriculture ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D42",
                question = "Annual Income of the Household From Livestock / Dairy",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Livestock / Dairy ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D43",
                question = "Annual Income of the Household From selling animal meat",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From selling animal meat ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D44",
                question = "Annual Income of the Household From Labour (on-farm)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Labour (on-farm) ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D45",
                question = "Annual Income of the Household From Labour (off-farm activities such as construction etc.)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Labour (off-farm activities such as construction etc.) ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D46",
                question = "Annual Income of the Household From non-farm business activities",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From non-farm business activities ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D47",
                question = "Annual Income of the Household From Salary of HH member (migrated/non-migrated) ",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Salary of HH member (migrated/non-migrated)  ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D48",
                question = "Annual Income of the Household From fish farming",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From fish farming")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D49",
                question = "Annual Income of the Household From Remittances",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Remittances")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D410",
                question = "Annual Income of the Household From Pension",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Pension")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D411",
                question = "Annual Income of the Household From Aid",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Aid")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D412",
                question = "Annual Income of the Household From Other",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Income of the Household From Other")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D5",
                question = "Has income changed in the last 12 Months",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Increased"),
                            CheckBoxItems("2", "Decreased"),
                            CheckBoxItems("3", "No change"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "D6",
                question = "Has income changed in the last 24 Months",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Increased"),
                            CheckBoxItems("2", "Decreased"),
                            CheckBoxItems("3", "No change"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "E1",
                question = "Total Area under cultivation (in Ha.)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Total Area under cultivation (in Ha.)")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "E2",
                question = "Area under Irrigation (in Ha.)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Area under Irrigation (in Ha.)")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "E3",
                question = "Rain fed Area (in Ha.)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Rain fed Area (in Ha.)")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "E4",
                question = "Leased in / Rent in Land, if any (in Ha.) ",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Leased in / Rent in Land, if any (in Ha.) ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "E5",
                question = "Sources of Irrigation",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Pond"),
                            CheckBoxItems("2", "Canal"),
                            CheckBoxItems("3", "River"),
                            CheckBoxItems("4", "Well"),
                            CheckBoxItems("5", "Tube well"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "E6",
                question = "Type of irrigation",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", " Flood"),
                            CheckBoxItems("2", "Sprinkler"),
                            CheckBoxItems("3", "Drip"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            //SECTION F not created
            








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
//        val answerEntity =
//        viewModelScope.launch {
//            repo.insertData(answerEntity)
//        }
    }

}
