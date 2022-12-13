package com.offline.form.builder.ui.home

import android.text.InputType
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.offline.form.builder.R
import com.offline.form.builder.data.db.AnswerEntity
import com.offline.form.builder.utils.*
import com.pradeep.form.simple_form.model.Form
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: HomeRepository = HomeRepository()
) : ViewModel() {

    val questions: List<Question> by lazy {
        listOf(
            Question(
                id = "A1",
                question = "A1 Name of the Respondent",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter your name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A2",
                question = "A2 District Name ",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter District Name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A3",
                question = "A3 Block Name",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter name of Block")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A4",
                question = "A4 Agri-Camp Name",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Agri-Camp Name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A5",
                question = "A5 Village Name",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Village Name")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A6",
                question = "A6 Household Serial No.",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Household Serial No.")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A7",
                question = "A7 Consent from the Respondent",
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
                question = "A8  Age of Respondent",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Age of Respondent")
                ),
                validate = NumberInputValidation(0, 100),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A9",
                question = "A9 Gender of the Respondent",
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
                question = "A10 Mobile number of respondent / House Hold member",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_PHONE,
                        "Enter Mobile number of respondent / House Hold member"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "A11",
                question = "A11 Are you Head of the Family",
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
                question = "A13 Gender of the head of Household",
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
                question = "A14 Relationship of respondent to Head of HH",
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
                question = "A15 Marital status of respondent",
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
                question = "A16 Highest Education level of respondent",
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
                question = "B1 How many people are in this household",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter no. of people")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "B2",
                question = "B2 Enter your family members ",
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
                id = "B12",
                question = "B12 What is the most common health related issue/disease in your area?",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "Enter the most common health related issue/disease in the area"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "B13",
                question = "B13 Is the family receiving Government support for medical expenses?",
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
                question = "B14 Is the family receiving Government support for educational expenses for children in the family??",
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
                question = "C1 What is the main source of drinking water for members of your household?",
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
                question = "C2 What kind of toilet facility does your household use?",
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
                question = "C2(a) How do you dispose of domestic waste?",
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
                question = "C3 Do you produce enough food to the last year?",
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
                id = "C4(a)",
                question = "C4(a) What energy source does your household rely for cooking and lighting? 1.=,  2=, 3=, 4=,  5=, 6=, 7= , 8.= (Specify) ",
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
                question = "C4(b) Is your household involved in crop production?",
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
                question = "C4(c) What does your household use to cultivate your farmland?",
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
            Question(
                id = "C51",
                question = "C51 Does any member of your household own Chickens or other poultry?",
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
                id = "C51.1",
                question = "C51.1 If response to is Yes, how much",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "C52",
                question = "C52 Does any member of your household own Sheep?",
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
                id = "C52.1",
                question = "C52.1 If response to is Yes, how much",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "C53",
                question = "C53 Does any member of your household own Goats?",
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
                id = "C53.1",
                question = "C53.1 If response to is Yes, how much",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "C54",
                question = "C54 Does any member of your household own Cattle?",
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
                id = "C54.1",
                question = "C54.1 If response to is Yes, how much",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "C55",
                question = "C55 Does any member of your household own Pigs?",
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
                id = "C55.1",
                question = "C55.1 If response to is Yes, how much",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "C56",
                question = "C56 Does any member of your household own others?",
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
                id = "C56.1",
                question = "C56.1 If response to is Yes, how much",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number")
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "C6",
                question = "C6 Value of house hold assets",
                options = listOf(
                    OptionType.Button(
                        "Enter Value of house hold assets",
                        object : ButtonAction{
                            override fun doAction(view: View, question: Question) {
                                view.findNavController()
                                    .navigate(
                                        R.id.action_nav_home_to_houseHoldAssetsFragment,
                                        bundleOf("formKey" to question.id)
                                    )
                            }

                        }
                    )
                ),
                isOptional = true,
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "D1",
                question = "D1 Annual Household Income (in Kwacha)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Household Income (in Kwacha)"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D2",
                question = "D2 Number of earning household members",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Number of earning household members"
                    )
                ),
                validate = NumberInputValidation(0, 100),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D3",
                question = "D3 Income earned by Women household members (in Kwacha)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Income earned by Women household members (in Kwacha)"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D41",
                question = "D41 Annual Income of the Household From Farming / Agriculture",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Farming / Agriculture "
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D42",
                question = "D42 Annual Income of the Household From Livestock / Dairy",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Livestock / Dairy "
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D43",
                question = "D43 Annual Income of the Household From selling animal meat",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From selling animal meat "
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D44",
                question = "D44 Annual Income of the Household From Labour (on-farm)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Labour (on-farm) "
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D45",
                question = "D45 Annual Income of the Household From Labour (off-farm activities such as construction etc.)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Labour (off-farm activities such as construction etc.) "
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D46",
                question = "D46 Annual Income of the Household From non-farm business activities",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From non-farm business activities "
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D47",
                question = "D47 Annual Income of the Household From Salary of HH member (migrated/non-migrated) ",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Salary of HH member (migrated/non-migrated)  "
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D48",
                question = "D48 Annual Income of the Household From fish farming",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From fish farming"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D49",
                question = "D49 Annual Income of the Household From Remittances",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Remittances"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D410",
                question = "D410 Annual Income of the Household From Pension",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Pension"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D411",
                question = "D411 Annual Income of the Household From Aid",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Aid"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D412",
                question = "D412 Annual Income of the Household From Other",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Income of the Household From Other"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "D5",
                question = "D5 Has income changed in the last 12 Months",
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
                question = "D6 Has income changed in the last 24 Months",
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
                question = "E1 Total Area under cultivation (in Ha.)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Total Area under cultivation (in Ha.)"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "E2",
                question = "E2 Area under Irrigation (in Ha.)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Area under Irrigation (in Ha.)"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "E3",
                question = "E3 Rain fed Area (in Ha.)",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Rain fed Area (in Ha.)"
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "E4",
                question = "E4 Leased in / Rent in Land, if any (in Ha.) ",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Leased in / Rent in Land, if any (in Ha.) "
                    )
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "E5",
                question = "E5 Sources of Irrigation",
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
                question = "E6 Type of irrigation",
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

            Question(
                id = "G1",
                question = "G1 Are you aware of Cashew Infrastructure Development Project (CIDP) being implemented in the District?",
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

            //G2 - G4 not created

            //H1 not created

            Question(
                id = "H2",
                question = "H2 Have you received/purchased new planting material in the last five years (2016-21)",
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
                id = "H3",
                question = "H3 From where did you received/purchased planting material for new plantation",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Cashew Nursery at ZARI, Simulumbe Station"),
                            CheckBoxItems("2", "Clone Garden"),
                            CheckBoxItems(
                                "3",
                                "Public demonstration nurseries(at Namushakende / Nangweshi / Kalabo Farmer Training Centres (FTCs))"
                            ),
                            CheckBoxItems("4", "Community Nursery developed under CIDP"),
                            CheckBoxItems("5", "Any other source"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "H4",
                question = "H4 How far is the nearest nursery/planting material source from your place (in Kms) ",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter (in Kms) ")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "H5",
                question = "H5 How will you rate the planting material used at your farm on a scale of “1 to 5”, wherein “1 “is poor and “5” is the best?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter Value")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "H6",
                question = "H6  Can you mention three benefits that you think the community has received from nurseries promoted under the project?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Benefits")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "H7",
                question = "H7 Did you get your Cashew plantation rejuvenated/rehabilitated under the project in last five years (2016-21)",
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
                id = "H8",
                question = "H8 If response to G7 is Yes, please mention number of trees rejuvenated/rehabilitated",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter number of trees rejuvenated/rehabilitated")
                ),
                validate = NumberInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "H9",
                question = "H9 Can you please mention specific support received for cashew plantation rejuvenation/rehabilitation",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter Benefits")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "H10",
                question = "H10 Did the productivity of the cashew plantation  improve because of rejuvenation?",
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
                id = "H11",
                question = "H11 Did you get bare area in your cashew plantation replanted under the project in last five years (2016-21)",
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
                id = "H13",
                question = "H13 Can you please mention specific support received for replantation in bare are in orchard",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "H14",
                question = "H14 Did the productivity of the cashew plantation improved due to replantation?",
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
                id = "H16",
                question = "H16 Did you bring new area under cashew production during CIDP project in the last five years (2016-21)",
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
                id = "H18",
                question = "H18 Can you please mention specific support received for bringing new area under the cashew production ?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "H19",
                question = "H19 Is yield levels of new orchard is higher than the old orchards?",
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
                id = "I1",
                question = "I1 Did you or anyone in your family receive any training under the project in the last five years (2016-21)",
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

            //I2 to I4 not written
            Question(
                id = "J1",
                question = "J1 Where do you sell the cashew nuts?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "at village level"),
                            CheckBoxItems("2", "In nearest town"),
                            CheckBoxItems("3", "At district level"),
                            CheckBoxItems("4", "outside the district"),
                            CheckBoxItems("5", "Outside the Country (export)"),
                            CheckBoxItems("6", "Any other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "J2",
                question = "J2 Can you please mention the name of the market?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "J3",
                question = "J3 How far is the market from your place? (in Kms)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "J5",
                question = "J5 How do you pack the Cashew Nuts for marketing?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "In gunny bags / plastic bags"),
                            CheckBoxItems("2", "In plastic crates"),
                            CheckBoxItems("3", "in wooden baskets"),
                            CheckBoxItems("4", "Open sacks"),
                            CheckBoxItems("5", "Any other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "J6",
                question = "J6 How do you transport Cashew nuts to the marketplace?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "In carts"),
                            CheckBoxItems("2", "In Auto / tempo"),
                            CheckBoxItems("3", "in Public transport (bus etc.)"),
                            CheckBoxItems("4", "Pick-up"),
                            CheckBoxItems("5", "Mini truck"),
                            CheckBoxItems("6", "Any other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "J7",
                question = "J7 How is the connectivity of the market to your place? ",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "walking track/uncleared path"),
                            CheckBoxItems("2", "Feeder road"),
                            CheckBoxItems("3", "Trunk /Main/District road"),
                            CheckBoxItems("4", "Any other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "J8",
                question = "J8 Whom do you sell your Cashew nuts in the market?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Aggregator"),
                            CheckBoxItems("2", "Wholesaler"),
                            CheckBoxItems("3", "Primary processor"),
                            CheckBoxItems("4", "Large processor"),
                            CheckBoxItems("5", "Cooperative"),
                            CheckBoxItems("6", "Any other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "J9",
                question = "J9 What is the type of market, in which you sell Cashew Nuts? ",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Community Collection Centre"),
                            CheckBoxItems("2", "Bulking facility"),
                            CheckBoxItems("3", "Wholesale market"),
                            CheckBoxItems("4", " At the plant of processor"),
                            CheckBoxItems("5", "Any other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),

            //J10 not done

            Question(
                id = "J11",
                question = "J11 What are the main challenges in marketing Cashew Nuts?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Lack of markets"),
                            CheckBoxItems("2", "Lack of marketing infrastructure"),
                            CheckBoxItems("3", "Lack of market information"),
                            CheckBoxItems("4", "Poor Communication"),
                            CheckBoxItems("5", "Bad Road infrastructure"),
                            CheckBoxItems("6", "Other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "J12",
                question = "J12 What are the sources of market information?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Fellow farmers"),
                            CheckBoxItems("2", "Traders"),
                            CheckBoxItems("3", "Government extension officers"),
                            CheckBoxItems("4", "CIDP Project staff"),
                            CheckBoxItems("5", "CIDP Project partners"),
                            CheckBoxItems("6", "Any Other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "K1",
                question = "K1 Have you been  availed any loan/grant  for Cashew Production",
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
                id = "K3",
                question = "K3 What is the source of the loan / financing?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Commercial bank "),
                            CheckBoxItems("2", "Cooperative Bank"),
                            CheckBoxItems("3", "Micro-credit Institutions"),
                            CheckBoxItems("4", "Family and friends"),
                            CheckBoxItems("5", "other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "K4",
                question = "K4 What is the purpose of loan / Funding?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Land preparation "),
                            CheckBoxItems("2", "Irrigation infrastructure at farm"),
                            CheckBoxItems("3", "Planting material procurement"),
                            CheckBoxItems("4", "Purchase of farm machinery / tractor"),
                            CheckBoxItems("5", "Any other"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "K5",
                question = "K5 What has been annual rate of interest for the loan amount?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "K6",
                question = "K6 What is the repayment period for the loan?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "K7",
                question = "K7 Did the loan require mortgage of property or any guarantee?",
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
                id = "K8",
                question = "K8 How will you rate ease of availing loan for cashew production?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Very Easy"),
                            CheckBoxItems("2", "Somewhat easy"),
                            CheckBoxItems("3", "Difficult"),
                            CheckBoxItems("4", "Very Difficult"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "L",
                question = "L Farmers groups and associations",
                options = listOf(
                    OptionType.Button("Farmers groups and associations", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_farmerGroupAssociation,
                                    bundleOf("formKey" to question.id)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "M1",
                question = "M1 How satisfied are you with the support received under the Cashew Infrastructure Development Project (CIDP)? ",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Very satisfied"),
                            CheckBoxItems("2", "Satisfied"),
                            CheckBoxItems("3", "Neutral"),
                            CheckBoxItems("4", "Dissatisfied"),
                            CheckBoxItems("5", "Very dissatisfied"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "M2",
                question = "M2 How satisfied are you with the CIDP program intervention for women and youth engagement? ",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Very satisfied"),
                            CheckBoxItems("2", "Satisfied"),
                            CheckBoxItems("3", "Neutral"),
                            CheckBoxItems("4", "Dissatisfied"),
                            CheckBoxItems("5", "Very dissatisfied"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "M3",
                question = "M3  Are there any changes in women involvement in household decision making after the CIDP program?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes(women role increased in decision marking)"),
                            CheckBoxItems("2", "No"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "M4",
                question = "M4 Are there any changes in women’s incomes and welfare, including nutrition  as a result of the Cashew project interventions?",
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
                id = "M5",
                question = "M5 Are you willing to participate in similar projects in the future?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Extremely unlikely"),
                            CheckBoxItems("2", "Unlikely"),
                            CheckBoxItems("3", "Neutral"),
                            CheckBoxItems("4", "Likely"),
                            CheckBoxItems("5", "extremely Likely"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "M6",
                question = "M6 How satisfied are you with the support  of the extension staff?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Very satisfied"),
                            CheckBoxItems("2", "Satisfied"),
                            CheckBoxItems("3", "Neutral"),
                            CheckBoxItems("4", "Dissatisfied"),
                            CheckBoxItems("5", "Very dissatisfied"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "M7",
                question = "M7 Which aspect of the Cashew Infrastructure Development Project (CIDP) should  be improved?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "behaviour of extension staff"),
                            CheckBoxItems("2", "provision of inputs"),
                            CheckBoxItems("3", "communication"),
                            CheckBoxItems("4", "marketing and processing"),
                            CheckBoxItems("5", "capacity building"),
                            CheckBoxItems("6", "access to financial resources"),
                            CheckBoxItems("6", "others (please mention)"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "M8",
                question = "M8 Please provide any suggestions or comments regarding the Cashew Infrastructure Development Project (CIDP)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "Enter")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),


            )
    }

    private val answers = mutableMapOf<String, Any>()

    val isEnabled = MutableLiveData(false)

    fun valueEntered(key: String, value: String) {
        Log.e("TAG", "valueEntered: Value is here $key $value")
        answers[key] = value
        checkAndUpdateButton()
    }

    private fun checkAndUpdateButton() {
        viewModelScope.launch(Dispatchers.Default) {
            questions.forEach {
                if (!it.isOptional && answers[it.id] == null) {
                    Log.e("TAG", "checkAndUpdateButton: error is jere $it")
                    isEnabled.postValue(false)
                    return@launch
                }
            }
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
                data = Gson().toJson(questions.associate { it.id to answers[it.id] }),
                createdAt = System.currentTimeMillis()
            )
            repo.insertData(answerEntity)
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

}
