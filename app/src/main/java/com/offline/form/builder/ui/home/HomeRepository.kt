package com.offline.form.builder.ui.home

import android.text.InputType
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.offline.form.builder.OfflineFormApp
import com.offline.form.builder.R
import com.offline.form.builder.data.db.AnswerEntity
import com.offline.form.builder.data.db.AnswersDao
import com.offline.form.builder.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(
    private val answersDao: AnswersDao = OfflineFormApp.db.answersDao()
) {

    val questions: List<Question> by lazy {
        mutableListOf(
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "S2 8.1.a 1",
                question = "\n8. LIVELIHOODS\n8.1 Farming\n8.1.A Are you or any members of your household involved in cultivating any farmland?",
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
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
                id = "S2 9.1.a",
                question = "9. FINANCIAL SERVICES\n9.1 Financial Products\nHas any member of the Household participated in any financial literacy training programs from the project?",
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
                id = "S2 9.1.b",
                question = "Are any of these financial products or services available in the community?",
                options = listOf(
                    OptionType.Button("Select from the financial products", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_financialProducts,
                                    bundleOf("formKey" to question.id)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 9.1.c.1",
                question = "Are you aware of financial services listed above?",
                options = listOf(
                    OptionType.Button("Select from the financial products", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_financialProducts,
                                    bundleOf("formKey" to question.id)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 9.1.c.2",
                question = "Did you or any member of the household use any of these financial products or services during the [reference period] ?",
                options = listOf(
                    OptionType.Button("Select from the financial products", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_financialProducts,
                                    bundleOf("formKey" to question.id)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S2 9.1.c.3",
                question = "What is the distance of bank branch/financial institution from your household? (kms)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "Enter distance")
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
                id = "S2 9.1.f",
                question = "What choice of Financial Service Provider was used?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Commercial Bank"),
                            CheckBoxItems("2", "Micro Finance Institutions"),
                            CheckBoxItems("3", "SACCO/Cooperative"),
                            CheckBoxItems("4", "Contract Farming Company"),
                            CheckBoxItems("5", "Village based savings/loan association"),
                            CheckBoxItems("6", "Insurance Company"),
                            CheckBoxItems("7", "Local money lender"),
                            CheckBoxItems("8", "Friends/Family"),
                            CheckBoxItems("9", "Church"),
                            CheckBoxItems("0", "Other")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "S2 9.1.f 1",
                question = "(If you choose OTHER above)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "S2 9.1.g",
                question = "What was the money used for after accessing it from the selected Financial Service Provider?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Purchase inputs"),
                            CheckBoxItems("2", "Invest in business"),
                            CheckBoxItems("3", "Productive assets"),
                            CheckBoxItems("4", "Daily consumption"),
                            CheckBoxItems("5", "Education"),
                            CheckBoxItems("6", "Health expenses"),
                            CheckBoxItems("7", "House and property"),
                            CheckBoxItems("8", "Repayment"),
                            CheckBoxItems("9", "Other")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.Switch
            ),
            Question(
                id = "S2 9.1.g 1",
                question = "(If you choose OTHER above)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
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
                id = "S2 9.1.j",
                question = "In general, how satisfied you with the services are provided by [FINANCIAL SERVICE PROVIDER]?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Very satisfied"),
                            CheckBoxItems("2", "Somewhat satisfied"),
                            CheckBoxItems("3", "Somewhat dissatisfied"),
                            CheckBoxItems("4", "Very dissatisfied")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "S2 9.1.k",
                question = "Do you still use [FINANCIAL SERVICE]",
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
                id = "S2 9.1.l",
                question = "Does any member of your household have a bank account?",
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
                id = "S2 9.1.m",
                question = "How do members of your household save money?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "AT HOME"),
                            CheckBoxItems("2", "LENDING"),
                            CheckBoxItems("3", "PHYSICAL ASSETS"),
                            CheckBoxItems("4", "BUSINESS INVESTMENT"),
                            CheckBoxItems("5", "DON'T SAVE"),
                            CheckBoxItems("6", "OTHER")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.Switch
            ),
            Question(
                id = "S2 9.1.m 1",
                question = "(If you choose OTHER in above)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "S2 9.2.a",
                question = "9.2 Microfinance Question\nAre you aware of financial Micro insurance?",
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
                id = "S2 9.2.b",
                question = "Has your [FINANCIAL SERVICE PROVIDER] sensitized you on Micro loan/ credit insurance and its benefits",
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
                id = "S2 9.2.c",
                question = "If yes to the question above, what is your understanding of Micro Insurance?",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "Explain"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "S2 9.2.d",
                question = "What type of micro insurance are you aware of?",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "Explain"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "S2 9.2.e",
                question = "Have you had any if at all Micro credit/ loans that you have applied for insured?",
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
                id = "S2 9.3.1",
                question = "9.3 Loans & Borrowings\nDoes the household have any outstanding debt as on date?",
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
                id = "S2 9.3.2",
                question = "Has any member of the households taken any loan in the past one year preceding the survey?",
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
                id = "S2 9.3.3",
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
                id = "S2 9.4.1.a",
                question = "9.4 Microfinance Experience\nIs anybody in the household a member of any of the following groups?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "A)Self Help Groups", true),
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No"),
                            CheckBoxItems("3", "B)Joint Liability Groups(JLG)", true),
                            CheckBoxItems("3", "Yes"),
                            CheckBoxItems("4", "No"),
                            CheckBoxItems("5", "C)Livelihood Collectives", true),
                            CheckBoxItems("5", "Yes"),
                            CheckBoxItems("6", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
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
                id = "S2 10.1",
                question = "10. MOBILE PHONE BANKING\nDoes any member of your household own a cell phone?",
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
                id = "S2 10.2",
                question = "Do you transfer/receive money through cell phone money transfer business?",
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
                id = "S2 10.3",
                question = "What about making payments for goods/services?",
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
                id = "S2 11.a",
                question = "11. SHOCKS\nHave you experienced any [SHOCK]?",
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
                id = "S2 11.a 2",
                question = "If YES, how severe was the shock?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "S2 11.b",
                question = "How severe was the [SHOCK]?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "SEVERE"),
                            CheckBoxItems("2", "MILD"),
                            CheckBoxItems("3", "NOT SEVERE")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "S2 11.c",
                question = "When did the last one occur?",
                options = listOf(
                    OptionType.InputField(
                        InputType.TYPE_CLASS_TEXT,
                        "MONTH THAT SHOCK BEGAN"
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "S2 11.d",
                question = "How many times in the past 12 months?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "")
                ),
                validate = NumberInputValidation(0, Int.MAX_VALUE),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "11.1.a",
                question = "Was your household affected by any of the following distress events/ emergencies in the last 10 years preceding the survey?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "YES"),
                            CheckBoxItems("2", "NO"),
                        )
                    )
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "S2 11.1.b",
                question = "If yes, then what strategies did you adopt to cope with the event?",
                options = listOf(
                    OptionType.Button(
                        "Select whether household was affected?",
                        object : ButtonAction {
                            override fun doAction(view: View, question: Question) {
                                view.findNavController()
                                    .navigate(
                                        R.id.action_nav_home_to_riskUncertainities,
                                        bundleOf("formKey" to question.id)
                                    )
                            }
                        }),
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "S3 12.a",
                question = "\n\nSECTION 3 : QUESTIONS ON DIETARY DIVERSITY\n12. DIET DIVERSITY\nHas any member of the Household participated in any project-supported activity designed to help improve nutrition?",
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
            Question(
                id = "S3 12.c",
                question = "Is there a woman aged 15-49 in this household who can answer a few nutrition questions?",
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
                id = "S2 12.c.1",
                question = "If yes identify within list of HouseHold members",
                options = listOf(
                    OptionType.Button("Select from the members list", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_houseHoldMembers,
                                    bundleOf("formKey" to question.id, "count" to Int.MAX_VALUE)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "13",
                question = "Please describe the foods (meals and snacks) that you ate or drank yesterday during the day and night, whether at home or outside the home. Start with the first food or drink of the morning.\n" +
                        "(Write down all foods and drinks mentioned. When composite dishes are mentioned, ask for the list of ingredients. When the respondent has finished, probe for meals and snacks not mentioned.)",
                options = listOf(
                    OptionType.Button("Enter your food meals", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController()
                                .navigate(
                                    R.id.action_nav_home_to_foodDayMeal,
                                    bundleOf("formKey" to question.id)
                                )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button
            ),
            Question(
                id = "14.1",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "CEREALS \n Example is Corn / maize, rice, wheat, sorghum, millet or any other grains or foods made from these (e.g.bread, noodles, porridge or other grain products) + insert local foods e . g . ugali, nshima, porridge or paste",
            ),
            Question(
                id = "14.2",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "WHITE ROOTS AND TUBERS \n Example is White potatoes, white yam, white cassava, or other foods made from roots",
            ),
            Question(
                id = "14.3",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "VITAMIN A RICH VEGETABLES AND TUBERS(11) \n Example is Pumpkin, carrot, squash, or sweet potato that are orange inside + other locally available vitamin A rich vegetables (e.g. red sweet pepper)",
            ),
            Question(
                id = "14.4",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "DARK GREEN LEAFY VEGETABLES \n Example is Dark green leafy vegetables, including wild forms + locally available vitamin A rich leaves such as amaranth, cassava leaves, kale, spinach",
            ),
            Question(
                id = "14.5",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "OTHER VEGETABLES \n Example is Other vegetables (e.g. tomato, onion, eggplant) + other locally available vegetables",
            ),
            Question(
                id = "14.6",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "VITAMIN A RICH FRUITS \n Example is Ripe mango, cantaloupe, apricot (fresh or dried), ripe papaya, dried peach, and 100% fruit juice made from these + other locally availablevitamin A rich fruits",
            ),
            Question(
                id = "14.7",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "OTHER FRUITS \n Example is Other fruits, including wild fruits and 100% fruit juice made from these",
            ),
            Question(
                id = "14.8",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "ORGAN MEAT \n Example is Liver, kidney, heart or other organ meats or blood – based foods",
            ),
            Question(
                id = "14.9",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "FLESH MEATS \n Example is Beef, pork, lamb, goat, rabbit, game, chicken, duck, other birds, insects",
            ),
            Question(
                id = "14.10",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "EGGS \n Example is Eggs from chicken, duck, guinea fowl or any other egg",
            ),
            Question(
                id = "14.11",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "FISH AND SEA FOOD \n Example is Fresh or dried fish or shellfish",
            ),
            Question(
                id = "14.12",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "LEGUMES, NUTS AND SEEDS \n Example is Dried beans, dried peas, lentils, nuts, seeds or foods made from these (eg. Hummus, peanut butter)",
            ),
            Question(
                id = "14.13",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "MILK AND MILK PRODUCTS \n Example is Milk, cheese, yoghurt or other milk products",
            ),
            Question(
                id = "14.14",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "OILS AND FATS \n Example is Oil, fats or butter added to food or used for cooking",
            ),
            Question(
                id = "14.15",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "SWEETS, SWEETENED AND ALCOHOLIC BEVERAGES  \n Example is sugar, honey, sweetened juice drinks, sugary foods such as chocolates, candies, cookies and cakes, alcoholic beverages and sweetened coffee andtea ",
            ),
            Question(
                id = "14.16",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "Yes"),
                            CheckBoxItems("2", "No")
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                question = "SPICES, CONDIMENTS AND UNSWEETENED BEVERAGES \n Example is Spices (black pepper, salt), condiments (soy sauce, hot sauce), and unsweetened coffee and tea.",
            ),
            Question(
                id = "14.17",
                question = "Did you or anyone in your household eat anything (meal or snack) OUTSIDE the home yesterday?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
        ).apply {
            listOf(
                "Farming/Crop production and sales",
                "Livestock production and sales",
                "Wage labour (local)",
                "Fishing",
                "Salaried work, including in-kind payment",
                "Sale of wild/bush products (incl charcoal)",
                "Other self-employment/own business",
                "Sale of land/other non-livestock assets",
                "Wild foods for household consumption",
                "Mining on community land",
                "Barter trade",
                "Remittances",
                "Gifts/inheritance",
                "Relief/Donations (church assistance, NGOs, etc.)",
                "Borrowing",
                "Bank interests/treasury bills",
                "Pension funds",
                "Social security",
                "Government transfers (e.g., FISP/ social cash transfer)",
                "Share-out from Saving Group",
            ).forEachIndexed { index, item ->
                add(
                    Question(
                        id = "15.$index",
                        question = item,
                        options = listOf(
                            OptionType.Button(
                                "Enter source, rank, stress, change, etc",
                                object : ButtonAction {
                                    override fun doAction(view: View, question: Question) {
                                        view.findNavController()
                                            .navigate(
                                                R.id.action_nav_home_to_sourcesOfIncome,
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
        }
    }

    suspend fun insertData(answerEntity: AnswerEntity) =
        withContext(Dispatchers.IO) { answersDao.insertAnswer(answerEntity) }

}
