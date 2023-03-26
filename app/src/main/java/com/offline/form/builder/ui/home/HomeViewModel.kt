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
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter size of field (in Ha)"
                    )
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
                id = "S2 8.1.d",
                question = "Who participates in the [CROP] cultivation?",
                options = listOf(
                    OptionType.Button("Select from the members list", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_houseHoldMembers,
                                    bundleOf("formKey" to question.id, "count" to 1)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 8.1.e",
                question = "Who in the household makes the decisions concerning crops to be planted, input use and the timing of cropping activities on the plot?",
                options = listOf(
                    OptionType.Button("Select from the members list", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_houseHoldMembers,
                                    bundleOf("formKey" to question.id, "count" to 2)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
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
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter Annual Household Income"
                    )
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "S2 8.2.b",
                question = "8.2.B Number of earning household members",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_NUMBER,
                        "Enter no. of earning household members"
                    )
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
            ),
            Question(
                id = "S2 9.1.d",
                question = "Who in the household decides to apply for the [FINANCIAL SERVICE] during the [reference period]?",
                options = listOf(
                    OptionType.Button("Select from the members list", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_houseHoldMembers,
                                    bundleOf("formKey" to question.id, "count" to 2)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 9.1.e",
                question = "Who in the household did use [FINANCIAL SERVICE] during the [reference period]?",
                options = listOf(
                    OptionType.Button("Select from the members list", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_houseHoldMembers,
                                    bundleOf("formKey" to question.id, "count" to 1)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 9.1.h",
                question = "Who made the decision about what to do with the money or item from this [FINANCIAL SERVICE PROVIDER]?",
                options = listOf(
                    OptionType.Button("Select from the members list", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_houseHoldMembers,
                                    bundleOf("formKey" to question.id, "count" to 2)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 9.1.i",
                question = "Who is responsible for repaying the money or item borrowed from this [FINANCIAL SERVICE PROVIDER]?",
                options = listOf(
                    OptionType.Button("Select from the members list", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_houseHoldMembers,
                                    bundleOf("formKey" to question.id, "count" to 2)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 9.2.3",
                question = "If yes, please provide the following details about the loans taken",
                options = listOf(
                    OptionType.Button("Enter details about the loans taken", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_loanBorrowingData,
                                    bundleOf("formKey" to question.id, "count" to 2)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 9.4.2",
                question = "If any response as ‘1’, then please provide the following details about the aforementioned microfinance groups with which the members of the household are associated – (Ask for last one year preceding the survey)",
                options = listOf(
                    OptionType.Button("Enter microfinance experience", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_microfinanceExperience,
                                    bundleOf("formKey" to question.id)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 12.b",
                question = "Who participated in any project-supported activity designed to help improve nutrition?",
                options = listOf(
                    OptionType.Button("Select from the members list", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_houseHoldMembers,
                                    bundleOf("formKey" to question.id)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
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
                        "Full name",
                        "Relation to Head of the Household",
                        "Sex",
                        "Age",
                        "LITERACY",
                        "SCHOOLING",
                        "Is he/she attending school?",
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

    fun submitDataWithSheetAndColumn(
        key: String,
        forms: MutableList<List<Form>>,
        sheetName: String,
        columnNames: List<String>
    ) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    sheetName,
                    it,
                    columnNames = columnNames
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

}
