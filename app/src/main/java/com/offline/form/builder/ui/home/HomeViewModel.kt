package com.offline.form.builder.ui.home

import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.offline.form.builder.R
import com.offline.form.builder.data.db.AnswerEntity
import com.offline.form.builder.utils.*
import com.pradeep.form.simple_form.model.Form
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: HomeRepository = HomeRepository()
) : ViewModel() {

//    val questions: List<Question> by lazy {
//        listOf(
//            Question(
//                id = "A1",
//                question = "Section A - RESPONDENT’S PROFILE \n\n A1 Name of the Respondent",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter your name")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "A2",
//                question = "A2 District Name ",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter District Name")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "A3",
//                question = "A3 Block Name",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter name of Block")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "A4",
//                question = "A4 Agri-Camp Name",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Agri-Camp Name")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "A5",
//                question = "A5 Village Name",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Village Name")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "A6",
//                question = "A6 Household Serial No.",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Household Serial No.")
//                ),
//                validate = NumberInputValidation(0, Int.MAX_VALUE),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "A7",
//                question = "A7 Consent from the Respondent",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No")
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//
//            Question(
//                id = "A8",
//                question = "A8  Age of Respondent",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Age of Respondent")
//                ),
//                validate = NumberInputValidation(0, 100),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "A9",
//                question = "A9 Gender of the Respondent",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Male"),
//                            CheckBoxItems("2", "Female")
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "A10",
//                question = "A10 Mobile number of respondent / House Hold member",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_PHONE,
//                        "Enter Mobile number of respondent / House Hold member"
//                    )
//                ),
//                validate = StringInputValidation(10),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "A11",
//                question = "A11 Are you Head of the Family",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No")
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "A12",
//                question = "A12 Name of the family Head (if response of A.11 is (2))",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_TEXT,
//                        "Enter Name of the family Head"
//                    )
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "A13",
//                question = "A13 Gender of the head of Household",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Male"),
//                            CheckBoxItems("2", "Female")
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "A14",
//                question = "A14 Relationship of respondent to Head of HH",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", " Head of Household"),
//                            CheckBoxItems("2", "Spouse"),
//                            CheckBoxItems("3", "Child (own/step)"),
//                            CheckBoxItems("4", "Parent /Parent in law"),
//                            CheckBoxItems("5", "Brother/sister"),
//                            CheckBoxItems("6", "Other relatives"),
//                            CheckBoxItems("7", "Unrelated"),
//
//                            )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Switch
//            ),
//            Question(
//                id = "A15",
//                question = "A15 Marital status of respondent",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Single (never married)"),
//                            CheckBoxItems("2", "Monogamously Married"),
//                            CheckBoxItems("3", "Polygamously Married"),
//                            CheckBoxItems("4", "Divorced"),
//                            CheckBoxItems("5", "Widowed"),
//                            CheckBoxItems("6", "Separated"),
//                            CheckBoxItems("7", "Cohabiting"),
//
//                            )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Switch
//            ),
//            Question(
//                id = "A16",
//                question = "A16 Highest Education level of respondent",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "No Formal Education"),
//                            CheckBoxItems("2", "Less than Grade 5"),
//                            CheckBoxItems("3", "Less than grade 10"),
//                            CheckBoxItems("4", "Less than Grade 12"),
//                            CheckBoxItems("5", "College Student"),
//                            CheckBoxItems("6", "University Undergraduate Student"),
//                            CheckBoxItems("7", "Tertiary Certificate;Diploma"),
//                            CheckBoxItems("8", "Bachelors Degree"),
//                            CheckBoxItems("9", "Masters Degree and Above."),
//
//                            )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Switch
//            ),
//            Question(
//                id = "B1",
//                question = "SECTION B: COMPOSITION OF HOUSEHOLD \n\n B1 How many people are in this household",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter no. of people")
//                ),
//                validate = NumberInputValidation(0, Int.MAX_VALUE),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "B2",
//                question = "B2 Enter your family members ",
//                options = listOf(
//                    OptionType.Button("Enter family members", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            val count = try {
//                                answers["B1"]?.toString()?.toInt() ?: -1
//                            } catch (e: Exception) {
//                                -1
//                            }
//
//                            if (count > 0) {
//                                view.findNavController()
//                                    .navigate(
//                                        R.id.action_nav_home_to_nav_gallery,
//                                        bundleOf("formKey" to question.id, "count" to count)
//                                    )
//                            } else {
//                                Toast.makeText(
//                                    view.context,
//                                    "Please enter proper value of B1 question",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button
//            ),
//            Question(
//                id = "B12",
//                question = "B12 What is the most common health related issue/disease in your area?",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_TEXT,
//                        "Enter the most common health related issue/disease in the area"
//                    )
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "B13",
//                question = "B13 Is the family receiving Government support for medical expenses?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No")
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "B14",
//                question = "B14 Is the family receiving Government support for educational expenses for children in the family??",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No")
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C1",
//                question = "SECTION C: Household Asset and Food Security \n\n C1 What is the main source of drinking water for members of your household?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "PIPED WATER"),
//                            CheckBoxItems("2", "Unprotected well"),
//                            CheckBoxItems("3", "PROTECTED WELL"),
//                            CheckBoxItems("4", "BOREHOLE"),
//                            CheckBoxItems("5", "RIVER, DAM OR STREAM"),
//                            CheckBoxItems("6", "OTHER"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Switch
//            ),
//            Question(
//                id = "C2",
//                question = "C2 What kind of toilet facility does your household use?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Bush"),
//                            CheckBoxItems("2", "Traditional Pit Latrine"),
//                            CheckBoxItems("3", "Improved Pit Latrine (Vip)"),
//                            CheckBoxItems("4", "Water- Borne Toilet"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C2(a)",
//                question = "C2(a) How do you dispose of domestic waste?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Rubbish Pit"),
//                            CheckBoxItems("2", "Burnt"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C3",
//                question = "C3 Do you produce enough food to the last year?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C3(a)",
//                question = "C3a If No, in which months do you run out of food?",
//                options = listOf(
//                    OptionType.Button(
//                        "Enter which month you run out?",
//                        object : ButtonAction {
//                            override fun doAction(view: View, question: Question) {
//                                view.findNavController()
//                                    .navigate(
//                                        R.id.action_nav_home_to_runOutFood,
//                                        bundleOf("formKey" to question.id)
//                                    )
//                            }
//
//                        }
//                    )
//                ),
//                isOptional = true,
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Button
//
//            ),
//            Question(
//                id = "C3(b)",
//                question = "3.b) If No, did you manage to buy adequate food for your household during the months you ran out of food?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                isOptional = true,
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C4(a)",
//                question = "C4(a) What energy source does your household rely for cooking and lighting?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Electricity"),
//                            CheckBoxItems("2", "LPG/Natural Gas"),
//                            CheckBoxItems("3", "Biogas"),
//                            CheckBoxItems("4", "Kerosene"),
//                            CheckBoxItems("5", "Charcoal"),
//                            CheckBoxItems("6", "Firewood"),
//                            CheckBoxItems("7", "Dung"),
//                            CheckBoxItems("8", "Other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Switch
//            ),
//            Question(
//                id = "C4(b)",
//                question = "C4(b) Is your household involved in crop production?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C4(c)",
//                question = "C4(c) What does your household use to cultivate your farmland?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Hand Tool (HOE/SPADE)"),
//                            CheckBoxItems("2", "Animal-Drawn Plow"),
//                            CheckBoxItems("3", "Tractor-Drawn Plow"),
//                            CheckBoxItems("4", "BOREHOLE"),
//                            CheckBoxItems("5", "Other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C 5.1",
//                question = "C 5.1 Does any member of your household own Chickens or other poultry?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C 5.1.1",
//                question = "C 5.1.1 If response to is Yes, how many",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "C 5.2",
//                question = "C 5.2 Does any member of your household own Sheep?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C 5.2.1",
//                question = "C 5.2.1 If response to is Yes, how many",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "C 5.3",
//                question = "C 5.3 Does any member of your household own Goats?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C 5.3.1",
//                question = "C 5.3.1 If response to is Yes, how many",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "C 5.4",
//                question = "C 5.4 Does any member of your household own Cattle?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C 5.4.1",
//                question = "C 5.4.1 If response to is Yes, how many",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "C 5.5",
//                question = "C 5.5 Does any member of your household own Pigs?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C 5.5.1",
//                question = "C 5.5.1 If response to is Yes, how many",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "C 5.6",
//                question = "C 5.6 Does any member of your household own others?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "C 5.6.1",
//                question = "C 5.6.1 If response to is Yes, how many",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "C6",
//                question = "C6 Value of house hold assets",
//                options = listOf(
//                    OptionType.Button(
//                        "Enter Value of house hold assets",
//                        object : ButtonAction {
//                            override fun doAction(view: View, question: Question) {
//                                view.findNavController()
//                                    .navigate(
//                                        R.id.action_nav_home_to_houseHoldAssetsFragment,
//                                        bundleOf("formKey" to question.id)
//                                    )
//                            }
//
//                        }
//                    )
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button
//            ),
//            Question(
//                id = "D1",
//                question = "SECTION D: INCOME PROFILE \n\n D1 Annual Household Income (in Kwacha)",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Household Income (in Kwacha)"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D2",
//                question = "D2 Number of earning household members",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Number of earning household members"
//                    )
//                ),
//                validate = NumberInputValidation(0, 100),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D3",
//                question = "D3 Income earned by Women household members (in Kwacha)",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Income earned by Women household members (in Kwacha)"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.1",
//                question = "D 4.1 Annual Income of the Household From Farming / Agriculture",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Farming / Agriculture "
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.2",
//                question = "D 4.2 Annual Income of the Household From Livestock / Dairy",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Livestock / Dairy "
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.3",
//                question = "D 4.3 Annual Income of the Household From selling animal meat",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From selling animal meat "
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.4",
//                question = "D 4.4 Annual Income of the Household From Labour (on-farm)",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Labour (on-farm) "
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.5",
//                question = "D 4.5 Annual Income of the Household From Labour (off-farm activities such as construction etc.)",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Labour (off-farm activities such as construction etc.) "
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.6",
//                question = "D 4.6 Annual Income of the Household From non-farm business activities",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From non-farm business activities "
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.7",
//                question = "D 4.7 Annual Income of the Household From Salary of HH member (migrated/non-migrated) ",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Salary of HH member (migrated/non-migrated)  "
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.8",
//                question = "D 4.8 Annual Income of the Household From fish farming",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From fish farming"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.9",
//                question = "D 4.9 Annual Income of the Household From Remittances",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Remittances"
//                    )
//                ),
//                validate = NumberInputValidation(-1),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.10",
//                question = "D 4.10 Annual Income of the Household From Pension",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Pension"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.11",
//                question = "D 4.11 Annual Income of the Household From Aid",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Aid"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D 4.12",
//                question = "D 4.12 Annual Income of the Household From Other",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Annual Income of the Household From Other"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "D5",
//                question = "D5 Has income changed in the last 12 Months",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Increased"),
//                            CheckBoxItems("2", "Decreased"),
//                            CheckBoxItems("3", "No change"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "D6",
//                question = "D6 Has income changed in the last 24 Months",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Increased"),
//                            CheckBoxItems("2", "Decreased"),
//                            CheckBoxItems("3", "No change"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//
//            Question(
//                id = "E1",
//                question = "SECTION E: LAND PROFILE \n\n E1 Total Area under cultivation (in Ha.)",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Total Area under cultivation (in Ha.)"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "E2",
//                question = "E2 Area under Irrigation (in Ha.)",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Area under Irrigation (in Ha.)"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "E3",
//                question = "E3 Rain fed Area (in Ha.)",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Rain fed Area (in Ha.)"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "E4",
//                question = "E4 Leased in / Rent in Land, if any (in Ha.) ",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter Leased in / Rent in Land, if any (in Ha.) "
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "E5",
//                question = "E5 Sources of Irrigation",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Pond"),
//                            CheckBoxItems("2", "Canal"),
//                            CheckBoxItems("3", "River"),
//                            CheckBoxItems("4", "Well"),
//                            CheckBoxItems("5", "Tube well"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Switch
//            ),
//            Question(
//                id = "E6",
//                question = "E6 Type of irrigation",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", " Flood"),
//                            CheckBoxItems("2", "Sprinkler"),
//                            CheckBoxItems("3", "Drip"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Switch
//            ),
//
//            Question(
//                id = "F",
//                question = "SECTION F: CROP PROFILE \n\n F Crop Profile",
//                options = listOf(
//                    OptionType.Button("Crop Profile", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            view.findNavController()
//                                .navigate(
//                                    R.id.action_nav_home_to_cropProfileFragment,
//                                    bundleOf("formKey" to question.id)
//                                )
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button
//            ),
//
//            Question(
//                id = "G1",
//                question = "SECTION G: Awareness of and Access to CIDP services \n\n G1 Are you aware of Cashew Infrastructure Development Project (CIDP) being implemented in the District?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "G2",
//                question = "G2 If the response to G.1 is “1=Yes”, please suggest, which component are you aware of and if you have benefited anything under the project",
//                options = listOf(
//                    OptionType.Button("Awareness and access", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            view.findNavController()
//                                .navigate(
//                                    R.id.action_nav_home_to_awarenessAccess,
//                                    bundleOf("formKey" to question.id)
//                                )
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button,
//                isOptional = true
//            ),
//            Question(
//                id = "H1",
//                question = "SECTION H: Cashew Production \n\n H.1 Can you please provide information on cashew plantation",
//                options = listOf(
//                    OptionType.Button("Cashew plantation", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            view.findNavController()
//                                .navigate(
//                                    R.id.action_nav_home_to_cashewProduction,
//                                    bundleOf("formKey" to question.id)
//                                )
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button,
//            ),
//            Question(
//                id = "H2",
//                question = "H2 Have you received/purchased new planting material in the last five years (2016-21)",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "H3",
//                question = "H3 From where did you received/purchased planting material for new plantation",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Cashew Nursery at ZARI, Simulumbe Station"),
//                            CheckBoxItems("2", "Clone Garden"),
//                            CheckBoxItems(
//                                "3",
//                                "Public demonstration nurseries(at Namushakende / Nangweshi / Kalabo Farmer Training Centres (FTCs))"
//                            ),
//                            CheckBoxItems("4", "Community Nursery developed under CIDP"),
//                            CheckBoxItems("5", "Any other source"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.Switch
//            ),
//            Question(
//                id = "H4",
//                question = "H4 How far is the nearest nursery/planting material source from your place (in Kms) ",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter (in Kms) ")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "H5",
//                question = "H5 How will you rate the planting material used at your farm on a scale of “1 to 5”, wherein “1 “is poor and “5” is the best?",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Value")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "H6",
//                question = "H6  Can you mention three benefits that you think the community has received from nurseries promoted under the project?",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Benefits")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "H7",
//                question = "H7 Did you get your Cashew plantation rejuvenated/rehabilitated under the project in last five years (2016-21)",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "H8",
//                question = "H8 If response to H7 is Yes, please mention number of trees rejuvenated/rehabilitated",
//                options = listOf(
//                    OptionType.InputField(
//                        InputType.TYPE_CLASS_NUMBER,
//                        "Enter number of trees rejuvenated/rehabilitated"
//                    )
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "H9",
//                question = "H9 Can you please mention specific support received for cashew plantation rejuvenation/rehabilitation",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Benefits")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "H10",
//                question = "H10 Did the productivity of the cashew plantation  improve because of rejuvenation?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "H11",
//                question = "H11 If response to H10 is “Yes”, please mention the improvement in yield (increase in yield Kg/Plant) ",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                isOptional = true,
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//
//
//            ),
//            Question(
//                id = "H12",
//                question = "H12 Did you get bare area in your cashew plantation replanted under the project in last five years (2016-21)",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "H13",
//                question = "H13 If response to H12 is “Yes”,please mention number of trees replanted ",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                isOptional = true,
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//
//
//            ),
//            Question(
//                id = "H14",
//                question = "H14 Can you please mention specific support received for replantation in bare are in orchard",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "H15",
//                question = "H15 Did the productivity of the cashew plantation improved due to replantation?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "H16",
//                question = "H16 If response to H15 is “Yes”,please mention the improvement in yield (increase in yield Kg/Plant)",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                isOptional = true,
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//
//
//            ),
//            Question(
//                id = "H17",
//                question = "H17 Did you bring new area under cashew production during CIDP project in the last five years (2016-21)",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "H18",
//                question = "H18 If response to H17 is “Yes”,please mention number of trees replanted.",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                isOptional = true,
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//
//
//            ),
//            Question(
//                id = "H19",
//                question = "H19 Can you please mention specific support received for bringing new area under the cashew production ?",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "H20",
//                question = "H20 Is yield levels of new orchard is higher than the old orchards?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "H21",
//                question = "H21 If response to H20 is “Yes”,please mention the improvement in yield (difference in yield Kg/Plant).",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                isOptional = true,
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//
//
//            ),
//            Question(
//                id = "I1",
//                question = "SECTION I: Training & Capacity building \n\n I1 Did you or anyone in your family receive any training under the project in the last five years (2016-21)",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "I2",
//                question = "I.2 If response to I.1 is “1=Yes”, please mention the number of training received by all the family members in this period",
//                options = listOf(
//                    OptionType.Button("Training by family", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            view.findNavController()
//                                .navigate(
//                                    R.id.action_nav_home_to_familyTraining,
//                                    bundleOf("formKey" to question.id)
//                                )
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button,
//                isOptional = true
//            ),
//            Question(
//                id = "I3",
//                question = "I.3 If the response to I.1 is “1=Yes”, Can you please mention the following details of training?",
//                options = listOf(
//                    OptionType.Button("Training", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            view.findNavController()
//                                .navigate(
//                                    R.id.action_nav_home_to_training,
//                                    bundleOf("formKey" to question.id)
//                                )
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button,
//                isOptional = true
//            ),
//            Question(
//                id = "I4",
//                question = "I.4 How have you adopted the knowledge gained through trainings? What benefits these trainings have brought in cashew production?",
//                options = listOf(
//                    OptionType.Button("Knowledge Training", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            view.findNavController()
//                                .navigate(
//                                    R.id.action_nav_home_to_knowledgeTraning,
//                                    bundleOf("formKey" to question.id)
//                                )
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button,
//                isOptional = true
//            ),
//            Question(
//                id = "J1",
//                question = "SECTION J: Cashew Processing and Marketing \n\n J1 Where do you sell the cashew nuts?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "at village level"),
//                            CheckBoxItems("2", "In nearest town"),
//                            CheckBoxItems("3", "At district level"),
//                            CheckBoxItems("4", "outside the district"),
//                            CheckBoxItems("5", "Outside the Country (export)"),
//                            CheckBoxItems("6", "Any other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "J2",
//                question = "J2 Can you please mention the name of the market?",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "J3",
//                question = "J3 How far is the market from your place? (in Kms)",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "J5",
//                question = "J5 How do you pack the Cashew Nuts for marketing?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "In gunny bags / plastic bags"),
//                            CheckBoxItems("2", "In plastic crates"),
//                            CheckBoxItems("3", "in wooden baskets"),
//                            CheckBoxItems("4", "Open sacks"),
//                            CheckBoxItems("5", "Any other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "J6",
//                question = "J6 How do you transport Cashew nuts to the marketplace?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "In carts"),
//                            CheckBoxItems("2", "In Auto / tempo"),
//                            CheckBoxItems("3", "in Public transport (bus etc.)"),
//                            CheckBoxItems("4", "Pick-up"),
//                            CheckBoxItems("5", "Mini truck"),
//                            CheckBoxItems("6", "Any other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "J7",
//                question = "J7 How is the connectivity of the market to your place? ",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "walking track/uncleared path"),
//                            CheckBoxItems("2", "Feeder road"),
//                            CheckBoxItems("3", "Trunk /Main/District road"),
//                            CheckBoxItems("4", "Any other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "J8",
//                question = "J8 Whom do you sell your Cashew nuts in the market?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Aggregator"),
//                            CheckBoxItems("2", "Wholesaler"),
//                            CheckBoxItems("3", "Primary processor"),
//                            CheckBoxItems("4", "Large processor"),
//                            CheckBoxItems("5", "Cooperative"),
//                            CheckBoxItems("6", "Any other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "J9",
//                question = "J9 What is the type of market, in which you sell Cashew Nuts? ",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Community Collection Centre"),
//                            CheckBoxItems("2", "Bulking facility"),
//                            CheckBoxItems("3", "Wholesale market"),
//                            CheckBoxItems("4", " At the plant of processor"),
//                            CheckBoxItems("5", "Any other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "J10",
//                question = "J10 What are the costs of marketting of cashew nuts and how these costs have changed in last 5 years?",
//                options = listOf(
//                    OptionType.Button("Costs of marketting", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            view.findNavController()
//                                .navigate(
//                                    R.id.action_nav_home_to_costAndMarketting,
//                                    bundleOf("formKey" to question.id)
//                                )
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button
//            ),
//            Question(
//                id = "J11",
//                question = "J11 What are the main challenges in marketing Cashew Nuts?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Lack of markets"),
//                            CheckBoxItems("2", "Lack of marketing infrastructure"),
//                            CheckBoxItems("3", "Lack of market information"),
//                            CheckBoxItems("4", "Poor Communication"),
//                            CheckBoxItems("5", "Bad Road infrastructure"),
//                            CheckBoxItems("6", "Other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "J12",
//                question = "J12 What are the sources of market information?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Fellow farmers"),
//                            CheckBoxItems("2", "Traders"),
//                            CheckBoxItems("3", "Government extension officers"),
//                            CheckBoxItems("4", "CIDP Project staff"),
//                            CheckBoxItems("5", "CIDP Project partners"),
//                            CheckBoxItems("6", "Any Other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "K1",
//                question = "SECTION K: Access to Finance \n\n K1 Have you been  availed any loan/grant  for Cashew Production",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "K2",
//                question = "K2 If Response to K1 is “1=Yes”, How much amount of a loan/grant have you been availed (in Kwacha)",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter amount")
//                ),
//                validate = NumberInputValidation(),
//                optionType = OptionTypeEnum.INPUT,
//                isOptional = true
//            ),
//            Question(
//                id = "K3",
//                question = "K3 What is the source of the loan / financing?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Commercial bank "),
//                            CheckBoxItems("2", "Cooperative Bank"),
//                            CheckBoxItems("3", "Micro-credit Institutions"),
//                            CheckBoxItems("4", "Family and friends"),
//                            CheckBoxItems("5", "other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "K4",
//                question = "K4 What is the purpose of loan / Funding?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Land preparation "),
//                            CheckBoxItems("2", "Irrigation infrastructure at farm"),
//                            CheckBoxItems("3", "Planting material procurement"),
//                            CheckBoxItems("4", "Purchase of farm machinery / tractor"),
//                            CheckBoxItems("5", "Any other"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "K5",
//                question = "K5 What has been annual rate of interest for the loan amount?",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "K6",
//                question = "K6 What is the repayment period for the loan?",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "K7",
//                question = "K7 Did the loan require mortgage of property or any guarantee?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "K8",
//                question = "K8 How will you rate ease of availing loan for cashew production?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Very Easy"),
//                            CheckBoxItems("2", "Somewhat easy"),
//                            CheckBoxItems("3", "Difficult"),
//                            CheckBoxItems("4", "Very Difficult"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "L",
//                question = "SECTION L: FARMER’S GROUPS or ASSOCIATIONS \n\n L Farmers groups and associations",
//                options = listOf(
//                    OptionType.Button("Farmers groups and associations", object : ButtonAction {
//                        override fun doAction(view: View, question: Question) {
//                            view.findNavController()
//                                .navigate(
//                                    R.id.action_nav_home_to_farmerGroupAssociation,
//                                    bundleOf("formKey" to question.id)
//                                )
//                        }
//                    })
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.Button
//            ),
//            Question(
//                id = "M1",
//                question = "SECTION M: PERCEPTION ABOUT CIDP \n\n M1 How satisfied are you with the support received under the Cashew Infrastructure Development Project (CIDP)? ",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Very satisfied"),
//                            CheckBoxItems("2", "Satisfied"),
//                            CheckBoxItems("3", "Neutral"),
//                            CheckBoxItems("4", "Dissatisfied"),
//                            CheckBoxItems("5", "Very dissatisfied"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "M2",
//                question = "M2 How satisfied are you with the CIDP program intervention for women and youth engagement? ",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Very satisfied"),
//                            CheckBoxItems("2", "Satisfied"),
//                            CheckBoxItems("3", "Neutral"),
//                            CheckBoxItems("4", "Dissatisfied"),
//                            CheckBoxItems("5", "Very dissatisfied"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "M3",
//                question = "M3  Are there any changes in women involvement in household decision making after the CIDP program?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes(women role increased in decision marking)"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "M4",
//                question = "M4 Are there any changes in women’s incomes and welfare, including nutrition  as a result of the Cashew project interventions?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Yes"),
//                            CheckBoxItems("2", "No"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "M5",
//                question = "M5 Are you willing to participate in similar projects in the future?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Extremely unlikely"),
//                            CheckBoxItems("2", "Unlikely"),
//                            CheckBoxItems("3", "Neutral"),
//                            CheckBoxItems("4", "Likely"),
//                            CheckBoxItems("5", "extremely Likely"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "M6",
//                question = "M6 How satisfied are you with the support  of the extension staff?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "Very satisfied"),
//                            CheckBoxItems("2", "Satisfied"),
//                            CheckBoxItems("3", "Neutral"),
//                            CheckBoxItems("4", "Dissatisfied"),
//                            CheckBoxItems("5", "Very dissatisfied"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "M7",
//                question = "M7 Which aspect of the Cashew Infrastructure Development Project (CIDP) should  be improved?",
//                options = listOf(
//                    OptionType.CheckBox(
//                        listOf(
//                            CheckBoxItems("1", "behaviour of extension staff"),
//                            CheckBoxItems("2", "provision of inputs"),
//                            CheckBoxItems("3", "communication"),
//                            CheckBoxItems("4", "marketing and processing"),
//                            CheckBoxItems("5", "capacity building"),
//                            CheckBoxItems("6", "access to financial resources"),
//                            CheckBoxItems("6", "others (please mention)"),
//                        )
//                    )
//                ),
//                validate = CheckboxInputValidation(),
//                optionType = OptionTypeEnum.CHECK_BOX
//            ),
//            Question(
//                id = "M8",
//                question = "M8 Please provide any suggestions or comments regarding the Cashew Infrastructure Development Project (CIDP)",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//            Question(
//                id = "N1",
//                question = "N1 Please enter Surveyor Name",
//                options = listOf(
//                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Surveyor Name")
//                ),
//                validate = StringInputValidation(),
//                optionType = OptionTypeEnum.INPUT
//            ),
//
//
//            )
//    }

    val questions: List<Question> by lazy {
        listOf(
            Question(
                id = "A1",
                question = "NAME OF THE RESPONDENT",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "Enter Name of the family Head"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "A2",
                question = "MARITAL STATUS",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Married"),
                            CheckBoxItems("2", "Divorced"),
                            CheckBoxItems("3", "Separated"),
                            CheckBoxItems("4", "Single"),
                            CheckBoxItems("5", "Widowed")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "A3",
                question = "MOBILE NUMBER",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_PHONE,
                        "Enter Mobile number of respondent"
                    )
                ),
                validate = StringInputValidation(10),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "A4",
                question = "VILLAGE/AREA NAME",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Village/Area Name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "A5",
                question = "DISTRICT",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter District Name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "A6",
                question = "NAME OF SAVINGS GROUP/COOP/SACCO/AGENT",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "Enter name of Savings Group/COOP/SACCO?Agent"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "A7",
                question = "NAME OF CHAIRPERSON",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter name of Chairperson")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "A8",
                question = "CHAIRPERSON'S NUMBER",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter number of Chairperson")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "A9",
                question = "NAME OF IMPLEMENTING PARTNER",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "Enter Name of the Implementing Partner"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "S1",
                question = "\n\nSECTION 1 : HOUSEHOLD DEMOGRAPHICS\nEnter your family members ",
                options = listOf(
                    OptionType.Button("Enter family members", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_nav_gallery,
                                    bundleOf("formKey" to question.id)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 1.a",
                question = "\n\nSECTION 2 : SURVEY\n1. DISABILITY: TO RESPONDENT OR EACH HH MEMBER\n1.A Do you have difficulty seeing, even if wearing glasses?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "No, no difficulty"),
                            CheckBoxItems("2", "Yes, some difficulty"),
                            CheckBoxItems("3", "Yes, a lot of difficulty"),
                            CheckBoxItems("4", "Cannot do it at all")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 1.b",
                question = "1.B Do you have difficulty hearing, even if using a hearing aid?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "No, no difficulty"),
                            CheckBoxItems("2", "Yes, some difficulty"),
                            CheckBoxItems("3", "Yes, a lot of difficulty"),
                            CheckBoxItems("4", "Cannot do it at all")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 1.c",
                question = "1.C Do you have difficulty walking or climbing steps",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "No, no difficulty"),
                            CheckBoxItems("2", "Yes, some difficulty"),
                            CheckBoxItems("3", "Yes, a lot of difficulty"),
                            CheckBoxItems("4", "Cannot do it at all")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 1.d",
                question = "1.D Do you have difficulty remembering or concentrating?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "No, no difficulty"),
                            CheckBoxItems("2", "Yes, some difficulty"),
                            CheckBoxItems("3", "Yes, a lot of difficulty"),
                            CheckBoxItems("4", "Cannot do it at all")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 1.e",
                question = "1.E Do you have difficulty (with self-care such as) washing all over or dressing?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "No, no difficulty"),
                            CheckBoxItems("2", "Yes, some difficulty"),
                            CheckBoxItems("3", "Yes, a lot of difficulty"),
                            CheckBoxItems("4", "Cannot do it at all")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 1.f",
                question = "1.F Do you have difficulty using your usual language, do you have difficulty communicating, (for example understanding or being understood by others)?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "No, no difficulty"),
                            CheckBoxItems("2", "Yes, some difficulty"),
                            CheckBoxItems("3", "Yes, a lot of difficulty"),
                            CheckBoxItems("4", "Cannot do it at all")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 2.a",
                question = "\n2. CHILD MORTALITY\n2.A Has any child who is a member of the household died in the last 12 months?",
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
                id = "S2 2.b",
                question = "2.B If YES, how many? (If answer of 2.A is YES)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number of children")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 3.a",
                question = "\n3. TYPE OF HOUSING\n3.A What type of housing do you currently live in?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "MUD AND GRASS THATCH"),
                            CheckBoxItems("2", "UN-BURNT BRICK AND IRON SHEETS"),
                            CheckBoxItems("3", "UN-BURNT BRICK AND GRASS THATCH"),
                            CheckBoxItems("4", "BURNT BRICKS AND IRON SHEETS"),
                            CheckBoxItems("5", "CONCRETE BRICKS AND IRON SHEETS"),
                            CheckBoxItems("6", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 3.b",
                question = "3.B On what basis does the household occupy the dwelling?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "PRIVATELY OWNED"),
                            CheckBoxItems("2", "FREE OF RENT"),
                            CheckBoxItems("3", "RENTED"),
                            CheckBoxItems("4", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "S2 3.c 1",
                question = "3.C What is the MAIN material of the dwelling floor?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "NATURAL FLOOR", true),
                            CheckBoxItems("1", "EARTH/SAND"),
                            CheckBoxItems("2", "DUNG"),
                            CheckBoxItems("3", "RUDIMENTARY FLOOR", true),
                            CheckBoxItems("3", "WOOD PLANKS"),
                            CheckBoxItems("4", "PALM/BAMBOO"),
                            CheckBoxItems("5", "FINISHED FLOOR", true),
                            CheckBoxItems("5", "POLISHED WOOD"),
                            CheckBoxItems("6", "TILED (CERAMIC, VINYL OR ASPHALT"),
                            CheckBoxItems("7", "CEMENT"),
                            CheckBoxItems("0", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "S2 3.d",
                question = "3.D What is the number of sleeping rooms in the dwelling?",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter no. of sleeping rooms"
                    )
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 4.a",
                question = "\n4. TYPE OF HOUSING\n4.A What is the MAIN source of drinking water for members of your household in the wet season?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "PIPED INTO HOUSE"),
                            CheckBoxItems("2", "PIPED INTO YARD OR PLOT"),
                            CheckBoxItems("3", "PUBLIC TAP"),
                            CheckBoxItems("4", "TUBEWELL/BOREHOLE WITH PUMP"),
                            CheckBoxItems("5", "PROTECTED DUG WELL"),
                            CheckBoxItems("6", "PROTECTED SPRING"),
                            CheckBoxItems("7", "RAINWATER COLLECTION"),
                            CheckBoxItems("8", "BOTTLED WATER"),
                            CheckBoxItems("9", "UNPROTECTED DUG WELL"),
                            CheckBoxItems("10", "UNPROTECTED SPRING"),
                            CheckBoxItems("11", "POND, RIVER OR STREAM"),
                            CheckBoxItems("12", "TANKER-TRUCK, VENDOR"),
                            CheckBoxItems("13", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 4.a 2",
                question = "(If you choose OTHER in 4.A)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 4.b",
                question = "4.B What is the main source of water during the dry season?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "PIPED INTO HOUSE"),
                            CheckBoxItems("2", "PIPED INTO YARD OR PLOT"),
                            CheckBoxItems("3", "PUBLIC TAP"),
                            CheckBoxItems("4", "TUBEWELL/BOREHOLE WITH PUMP"),
                            CheckBoxItems("5", "PROTECTED DUG WELL"),
                            CheckBoxItems("6", "PROTECTED SPRING"),
                            CheckBoxItems("7", "RAINWATER COLLECTION"),
                            CheckBoxItems("8", "BOTTLED WATER"),
                            CheckBoxItems("9", "UNPROTECTED DUG WELL"),
                            CheckBoxItems("10", "UNPROTECTED SPRING"),
                            CheckBoxItems("11", "POND, RIVER OR STREAM"),
                            CheckBoxItems("12", "TANKER-TRUCK, VENDOR"),
                            CheckBoxItems("13", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 4.b 2",
                question = "(If you choose OTHER in 4.B)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 4.c",
                question = "4.C How long does it take to go there, get water, and come back?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Water within premises"),
                            CheckBoxItems("2", "No answer or don't know")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 4.d",
                question = "4.D How is drinking water treated?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "BOIL"),
                            CheckBoxItems("2", "ADD CHLORINE"),
                            CheckBoxItems("3", "DON'T TREAT"),
                            CheckBoxItems("4", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 4.d 2",
                question = "(If you choose OTHER in 4.D)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 5.a",
                question = "\n5. SANITATION AND HEALTH\n5.A What kind of toilet facility does your household use?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "NO FACILITY/BUSH/FIELD"),
                            CheckBoxItems("2", "OPEN  PIT/TRADITIONAL PIT LATRINE"),
                            CheckBoxItems("3", "IMPROVED PIT LATRINE (VIP)"),
                            CheckBoxItems("4", "POUR FLUSH LATRINE"),
                            CheckBoxItems("5", "FLUSH TOILET WITH SOAK AWAY"),
                            CheckBoxItems("6", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 5.a 2",
                question = "(If you choose OTHER in 5.A)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 5.b",
                question = "5.B Is this toilet facility located within your dwelling, or yard or compound?",
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
                id = "S2 5.c",
                question = "5.C What kind of health facility is available in your vicinity?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "NO FACILITY"),
                            CheckBoxItems("2", "HOSPITAL"),
                            CheckBoxItems("3", "HEALTH CENTER"),
                            CheckBoxItems("4", "HEALTH CLINIC"),
                            CheckBoxItems("5", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 5.c 2",
                question = "(If you choose OTHER in 5.C)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 5.d",
                question = "5.D What is the distance of nearest health centre? (Km)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter distance")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 6.a",
                question = "\n6. FOOD SECURITY\n6.A In the past 12 months, did your household experience a hungry season?\n" +
                        "(The hungry season means the number of months a household does not have enough food because their own stores are depleted and they do not have money to buy food)",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 6.b",
                question = "6.B During what month did the hungry season begin?",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "MONTH THAT HUNGRY SEASON BEGAN"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 6.c",
                question = "6.C During what month did the hungry season end?",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "MONTH THAT HUNGRY SEASON ENDED"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 6.d",
                question = "6.D In the past 12 months, did your household experience a second hungry season?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 7.a",
                question = "\n7. HOUSEHOLD ASSET-RELATED QUESTIONS\n7.A Does your household have ....\nElectricity/Solar",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 7.a 2",
                question = "Radio",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 7.a 3",
                question = "Television",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 7.a 4",
                question = "Refrigerator",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "S2 7.a 5",
                question = "Others (Fan, decoder, etc.)",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 7.a 6",
                question = "(If you choose OTHER in 7.A)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 7.b",
                question = "7.B How would you describe your household assets base since taking part in project activities?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "INCREASED"),
                            CheckBoxItems("2", "DECREASED"),
                            CheckBoxItems("3", "NO CHANGE")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 7.c 1",
                question = "7.C Does any member of your household own...?\nBicycle",
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
                id = "S2 7.c 2",
                question = "Motorcycle or Scooter",
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
                id = "S2 7.c 3",
                question = "Car or Truck",
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
                id = "S2 7.c 4",
                question = "Others",
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
                id = "S2 7.c 5",
                question = "(If you choose OTHER in 7.C)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 7.d",
                question = "7.D What type of fuel does your household MAINLY use for cooking?",
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
                optionType = OptionTypeEnum.Switch
            ),

            Question(
                id = "S2 7.d 2",
                question = "(If you choose OTHER in 7.D)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.a 1",
                question = "\nLIVELIHOODS\n8.1 Farming\n8.1.A Are you or any members of your household involved in cultivating any farmland?",
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
                id = "S2 8.1.a 2",
                question = "If YES, what is the size of the field(Ha)?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter size of field (in Ha)")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.b 1",
                question = "8.1.B How did your household acquire the [PLOT]?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "INHERITED"),
                            CheckBoxItems("2", "PURCHASED OR LEASE"),
                            CheckBoxItems("3", "ALLOCATED BY GOVERNMENT"),
                            CheckBoxItems("4", "ALLOCATED BY LOCAL LEADER"),
                            CheckBoxItems("5", "RENTED IN"),
                            CheckBoxItems("6", "SHARE-CROPPED"),
                            CheckBoxItems("7", "BORROWED FOR FREE"),
                            CheckBoxItems("8", "CLEARED AND OCCUPIED"),
                            CheckBoxItems("9", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 8.1.b 2",
                question = "(If you choose OTHER in 8.1.B)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.c 1",
                question = "8.1.C What does your household use to cultivate MOST of your farmland?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "HAND TOOL (HOE/SPADE)"),
                            CheckBoxItems("2", "ANIMAL-DRAWN PLOUGH"),
                            CheckBoxItems("3", "TRACTOR-DRAWN PLOUGH"),
                            CheckBoxItems("4", "POWER TILLER"),
                            CheckBoxItems("5", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 8.1.c 2",
                question = "(If you choose OTHER in 8.1.C)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.f",
                question = "8.1.F Are you or any members of your household involved in any gardening?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 8.1.g",
                question = "8.1.G Is it irrigated?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 8.1.h",
                question = "8.1.H What is the source of irrigation water?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "PIPED INTO HOUSE"),
                            CheckBoxItems("2", "PIPED INTO YARD OR PLOT"),
                            CheckBoxItems("3", "PUBLIC TAP"),
                            CheckBoxItems("4", "TUBEWELL/BOREHOLE WITH PUMP"),
                            CheckBoxItems("5", "PROTECTED DUG WELL"),
                            CheckBoxItems("6", "PROTECTED SPRING"),
                            CheckBoxItems("7", "RAINWATER COLLECTION"),
                            CheckBoxItems("8", "BOTTLED WATER"),
                            CheckBoxItems("9", "UNPROTECTED DUG WELL"),
                            CheckBoxItems("10", "UNPROTECTED SPRING"),
                            CheckBoxItems("11", "POND, RIVER OR STREAM"),
                            CheckBoxItems("12", "TANKER-TRUCK, VENDOR"),
                            CheckBoxItems("13", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 8.1.h 2",
                question = "(If you choose OTHER in 8.1.H)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        ""
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.i",
                question = "8.1.I Apart from members of your household, did you use any labour in this farmland in the past 12 months?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            Question(
                id = "S2 8.1.j 1",
                question = "8.1.J Did you pay wages to the labourers?",
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
                id = "S2 8.1.j 2",
                question = "If YES, how much did you pay (ZMW)?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.k 1",
                question = "8.1.K Does any member of your household own any livestock?\nChickens or other poultry?",
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
                id = "S2 8.1.k 2",
                question = "If YES, how many?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.k 3",
                question = "Sheep?",
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
                id = "S2 8.1.k 4",
                question = "If YES, how many?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.k 5",
                question = "Goats",
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
                id = "S2 8.1.k 6",
                question = "If YES, how many?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.k 7",
                question = "Cattle",
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
                id = "S2 8.1.k 8",
                question = "If YES, how many?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.1.k 9",
                question = "Pigs",
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
                id = "S2 8.1.k 10",
                question = "If YES, how many?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),

            Question(
                id = "S2 8.2.a",
                question = "8.2 Household Income\n8.2.A Annual household Income (in Kwacha)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Annual Household Income")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.b",
                question = "8.2.B Number of earning household members",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter no. of earning household members")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.c",
                question = "8.2.C Income earned by Women household members (in Kwacha)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 1",
                question = "8.2.D Please provide different source-wise annual Income of the Household (in Kwacha)\nFrom Farming/Agriculture",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 2",
                question = "From selling milk",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 3",
                question = "From selling animal meat",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 4",
                question = "From Labour(off-farm activities such as construction etc.)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 5",
                question = "From Enterprise",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 6",
                question = "From Salary of HH member (migrated/non-migrated)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 7",
                question = "Remittances",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 8",
                question = "Pension",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),

            Question(
                id = "S2 8.2.d 9",
                question = "Aid/ Government Grant",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            )
        )
    }


    private val answers = mutableMapOf<String, Any>()

    val isEnabled = MutableLiveData(false)

    val errorText = MutableLiveData<String?>(null)

    fun valueEntered(key: String, value: String) {
        Log.e("TAG", "valueEntered: Value is here $key $value")
        answers[key] = value
        checkAndUpdateButton()
    }

    private fun checkAndUpdateButton() {
        viewModelScope.launch(Dispatchers.Default) {
            val necessaryQuestions = questions.subList(0, 7)
            val otherQuestions = questions.subList(8, questions.size)
            necessaryQuestions.forEach {
                if (!it.isOptional && answers[it.id] == null) {
                    errorText.postValue("Please fill ${it.id} question to enable submit button ")
                    isEnabled.postValue(false)
                    return@launch
                }
            }
            if (answers["A7"] == "2") {
                errorText.postValue(null)
                isEnabled.postValue(true)
                return@launch
            }
            otherQuestions.forEach {
                if (!it.isOptional && answers[it.id] == null) {
                    errorText.postValue("Please fill ${it.id} question to enable submit button ")
                    isEnabled.postValue(false)
                    return@launch
                }
            }
            errorText.postValue(null)
            isEnabled.postValue(true)
        }
    }

    fun getAnsIfAvailable(key: String): String? = answers[key]?.toString()

    fun clearValue(key: String) {
        Log.e("TAG", "clearValue: value removed $key")
        answers.remove(key)
        checkAndUpdateButton()
    }

    fun insertData() {
        viewModelScope.launch {
            val answerEntity = AnswerEntity(
                nameOfRespondent = answers["A1"]?.toString() ?: "",
                data = Gson().toJson(
                    questions.associate {
                        it.id to if (answers[it.id] != null) answers[it.id] else "NA"
                    }.toMutableMap().run {
                        put("Date", getCurrentDateTime().toString("dd/MM/yyyy"))
                        this
                    }),
                createdAt = System.currentTimeMillis()
            )
            repo.insertData(answerEntity)
            answers.clear()
        }
    }

    fun submitData(key: String, forms: List<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.USERS_SHEET,
                    it,
                    columnNames = listOf(
                        "B3",
                        "B4",
                        "B5",
                        "B6",
                        "B7",
                        "B8",
                        "B9",
                        "B10",
                        "B11",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitC6Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.HOUSE_HOLD_ASSETS_SHEET,
                    it,
                    columnNames = listOf(
                        "C6.1",
                        "C6.2",
                        "C6.3",
                        "C6.4",
                        "C6.5",
                        "C6.6",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitLData(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.FARMERS_ASSOCAITION,
                    it,
                    columnNames = listOf(
                        "L1",
                        "L2",
                        "L3",
                        "L4",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitJ10Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.CASHEW_COST_MARKETING,
                    it,
                    columnNames = listOf(
                        "J10.0",
                        "J10.1",
                        "J10.2",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitFData(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.CROP_PROFILE,
                    it,
                    columnNames = listOf(
                        "F1",
                        "F2",
                        "F3",
                        "F4",
                        "F5",
                        "F6",
                        "F7",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitG2Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.AWARENESS_ACCESS_CIDP,
                    it,
                    columnNames = listOf(
                        "Project Intervention",
                        "Project Intervention Type",
                        "G.2.a",
                        "G.2.b",
                        "G.2.c",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitH1Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.CASHEW_PRODUCTION,
                    it,
                    columnNames = listOf(
                        "Particular",
                        "Yr. 2015",
                        "Yr. 2016",
                        "Yr. 2017",
                        "Yr. 2018",
                        "Yr. 2019",
                        "Yr. 2020",
                        "Yr. 2021",
                        "Yr. 2022",
                        "Total",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitI3Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.I3_TRAINING,
                    it,
                    columnNames = listOf(
                        "I.3.1",
                        "I.3.2",
                        "I.3.3",
                        "I.3.4",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitI4Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.I4_KNOWLEDGE_TRANING,
                    it,
                    columnNames = listOf(
                        "I.4.0",
                        "I.4.1",
                        "I.4.2",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitC3aData(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.RUN_OUT_FOOD,
                    it,
                    columnNames = listOf("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D")
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun valueEnteredInCheckbox(key: String, value: String, checked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val array = answers[key]
            val list = if (array == null || array.toString().isEmpty()) {
                mutableListOf()
            } else {
                val type = object : TypeToken<List<String>>() {}.type
                Gson().fromJson<List<String>>(array.toString(), type).toMutableList()
            }
            Log.e("TAG", "valueEnteredInCheckbox: $checked $value")
            if (checked) {
                if (!list.contains(value)) {
                    list.add(value)
                }
            } else {
                if (list.contains(value)) {
                    list.remove(value)
                }
            }

            if (list.isEmpty()) {
                Log.e("TAG", "valueEntered: Value removed from here $key $list")
                answers.remove(key)
            } else {
                Log.e("TAG", "valueEntered: Value is here $key $list")
                answers[key] = Gson().toJson(list)
            }
            checkAndUpdateButton()
        }
    }

    fun getAnsForCheckboxIfAvailable(key: String, value: String): Boolean {
        val array = answers[key]
        val list = if (array == null || array.toString().isEmpty()) {
            mutableListOf()
        } else {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson<List<String>>(array.toString(), type).toMutableList()
        }
        return list.contains(value)
    }

    fun submitI2Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.FARMERS_ASSOCAITION,
                    it,
                    columnNames = listOf(
                        "I2.1",
                        "I2.2",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

}
