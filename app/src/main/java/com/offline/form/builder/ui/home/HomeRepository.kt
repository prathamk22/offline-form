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
                id = "1.1",
                question = "आपके परिवार के आय का प्राथमिक स्रोत क्या है ?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "पशुपालन/गव्यपालन"),
                            CheckBoxItems("2", "फसलों की खेती"),
                            CheckBoxItems("3", "कृषि आधारित दैनिक मजदूरी"),
                            CheckBoxItems("4", "मत्स्य पालन"),
                            CheckBoxItems("5", "ऑफ-फार्म/पशुपालन आधारित दैनिक मजदूरी"),
                            CheckBoxItems("6", "वस्तुओं का व्यापार"),
                            CheckBoxItems("7", "सार्वजनिक/सरकारी योजनाओं से लाभ"),
                            CheckBoxItems(
                                "8",
                                "घर से सुदूर रहने वाले पारिवारिक सदस्यों द्वारा भेजी गयी राशि से आय"
                            ),
                            CheckBoxItems("9", "अन्य, कृपया बताये"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "1.1.1",
                question = "कृपया अन्य बताये",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया अन्य बताये")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "1.2",
                question = "क्या आपके आय का यह प्राथमिक स्रोत किसी तरह नवाचार पर निर्भर करता है या उसका उपयोग करता है? (हाँ/नहीं)",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "1.2.1",
                question = "अगर उनके आय का प्राथमिक स्रोत कृषि उत्पादों की बिक्री नहीं है :\n\n आप कितने समय से यह कार्य कर रहे है (महीने में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया महीने बताये")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "1.2.2",
                question = "इस कार्य से आपको सालाना कितना आय हो जाता है ?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया आय बताये")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "1.3",
                question = "आपके साथ कितने व्यक्ति रहते है ? वे कौन है ?",
                options = listOf(
                    OptionType.Button("कृपया व्यक्ति बताये", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController().navigate(
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
                id = "1.4",
                question = "क्या आपके परिवार का कृषि उत्पादों की बिक्री के अलावे आय का कोई अन्य अतिरिक्त स्रोत है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "1.4.0",
                question = "कृपया अन्य अतिरिक्त स्रोत बताये",
                options = listOf(
                    OptionType.Button("कृपया अन्य अतिरिक्त स्रोत बताये", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController().navigate(
                                R.id.action_nav_home_to_costAndMarketting,
                                bundleOf("formKey" to question.id)
                            )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button,
                isOptional = true
            ),
            Question(
                id = "2.1",
                question = "सौर जल पंप का प्रयोग (Use of Solar Water Pump) \n\n आप कितनी बार सौर जल पंप का उपयोग करते हैं? ((प्रत्येक मौसम में प्रति माह उपयोग)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "बताये")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "2.2",
                question = "आपने पहली बार कब इस पंप का उपयोग प्रारंभ किया था (महीने में )",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "बताये")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "2.3",
                question = "आपके साथ और कितने परिवार या कृषक सौर जल पंप का प्रयोग करते है ?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "बताये")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "2.4",
                question = "आपके परिवार में किसे इस सौर जल पंप से लाभ हुआ है ?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "समय की बचत"),
                            CheckBoxItems("2", "मजदूरी की बचत"),
                            CheckBoxItems("3", "राजस्व/बिक्री में वृद्धि"),
                            CheckBoxItems("4", "उपज में वृद्धि"),
                            CheckBoxItems("5", "कृषि गतिविधियों के कार्यशील लागत में कमी"),
                            CheckBoxItems(
                                "6",
                                "मरुस्थलीकरण में कमी एवं मिट्टी के गुणवत्ता में सुधार"
                            ),
                            CheckBoxItems(
                                "7",
                                "जलवायु परिवर्तन के अनुकूल ढलने की क्षमता में सुधार"
                            ),
                            CheckBoxItems("8", "अन्य, कृपया उल्लेख करे"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.Switch
            ),
            Question(
                id = "2.5",
                question = "आपके साथ और कितने परिवार या कृषक सौर जल पंप का प्रयोग करते है ?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "बताये")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "2.6",
                question = "जब से आपने सौर जल पंप का उपयोग करना शुरू किया है तब से आपकी खेती के तौर-तरीके कैसे बदल गए हैं? लागू होने वाले सभी का चयन करें।",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "कोई बदलाव नहीं"),
                            CheckBoxItems("2", "फ़सल में बदलाव या नयी फसलों की शुरुआत"),
                            CheckBoxItems("3", "पुराणी फसलों के स्थान पर नयी फ़सल का उत्पादन"),
                            CheckBoxItems("4", "सिचाई व्यवस्था में बदलाव"),
                            CheckBoxItems("5", "राजस्व/उत्पाद में बढ़ोतरी"),
                            CheckBoxItems("6", "जल के प्रयोग में कमी"),
                            CheckBoxItems("7", "इंधन/गैस/डीजल के उपयोग में कमी "),
                            CheckBoxItems(
                                "8",
                                "खाद या कीटनासकके प्रयोग में कमी या कोई उपयोग नहीं "
                            ),
                            CheckBoxItems("9", "कृषि कार्यों में परिश्रम या प्रयास में परिवर्तन"),
                            CheckBoxItems("10", "अन्य, कृपया उल्लेख करे"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.Switch
            ),
            Question(
                id = "2.6.1",
                question = "कृपया अन्य बताये",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "2.7",
                question = "क्या आपने जलवायु परिवर्तन के अनुकूलन हेतु अपने कृषि पद्धतियों में कोई बदलाव किया है ?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "2.7.1",
                question = "अगर हाँ, आपने क्या परिवार्तन किया है ? लागू होने वाले सभी का चयन करें।",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "बुवाई की तारीख/मौसम बदल दिया गया "),
                            CheckBoxItems("2", "फ़सल में बदलाव किया गया "),
                            CheckBoxItems("3", "फसलों या कृषि गतिविधियों की विविधता में वृद्धि"),
                            CheckBoxItems("4", "कृषि कार्य में जल के उपयोग में बदलाव"),
                            CheckBoxItems(
                                "5",
                                "कृषि भूमि के इनपुट/उपचार में बदलाव (उदहारण: खाद, जुताई के तरीके, अन्य कृषि भूमि कार्य )"
                            ),
                            CheckBoxItems("6", "अन्य, कृपया उल्लेख करे"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.Switch
            ),
            Question(
                id = "2.8",
                question = "क्या सौर जल पंप के प्रयोग का कोई नकारात्मक प्रभाव पड़ा ?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "2.8.1",
                question = "अगर हाँ तो उन नकारात्मक प्रभावों का उल्लेख करे",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "2.9",
                question = "क्या सौर जल पंप में सुधार हेतु आपकी दृष्टि में कोई तरीका है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "2.9.1",
                question = "अगर हाँ तो कृपया उल्लेख करे",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "2.10",
                question = "आपने सौर जल पंप गतिविधि के उपयोग हेतु कितना भुगतान किया (भारतीय रुपये में )",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
            ),
            Question(
                id = "2.11",
                question = "क्या सौर जल पंप में सुधार हेतु आपकी दृष्टि में कोई तरीका है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX
            ),
            Question(
                id = "2.11.1",
                question = "अगर हाँ, तो आपने कितना सब्सिडी प्राप्त किया है ? ( भारतीय रुपये में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "2.11.2",
                question = "आपने कितना ऋण लिया है ? ( भारतीय रुपये में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "2.11.3",
                question = "आपने ऋण किससे लिया है ?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "बैंक"),
                            CheckBoxItems("2", "सूक्ष्म वित्त संस्था (MFI)"),
                            CheckBoxItems("3", "मित्र और परिवार"),
                            CheckBoxItems("4", "अन्य, कृपया उल्लेख करे"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "2.11.4",
                question = "अगर नहीं, क्या आप सौर जल पंप आधारित कार्यों के भुगतान के लिए वित्तीय सहयोग लेना चाहते है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "2.12",
                question = "क्या इस सौर जल पंप गतिविधि के परिणामस्वरूप पहले उपयोग की जाने वाली विधियों या उपकरणों की तुलना में वर्तमान कृषि गतिविधियों में समय की बचत होती है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "2.12.1",
                question = "अगर हाँ, अब आपके कितने पारिवारिक सदस्य या कर्मचारी इस कार्य / गतिविधि को करने से अपना समय बचाते है I (व्यक्तियों की संख्या)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "2.12.2",
                question = "इस कार्य को करने वाले प्रत्येक व्यक्ति, सौर जल पंप का उपयोग करने से प्रत्येक माह कितने घंटे की बचत कर लेते है I (कुल घंटे-प्रति व्यक्ति/प्रति माह)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "2.13",
                question = "1 से 10 के पैमाने पर आप सौर जल पंप गतिविधियों से कितने संतुष्ट है जहा 10 अंक का मतलब सबसे अधिक संतुष्ट है",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = NumberInputValidation(0, 10),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "2.14",
                question = "1-10 के पैमाने पर आपके द्वारा अन्य लोगों को सौर जल पंप की अनुशंसा करने की कितनी संभावना है? 10 अंक का मतलब सौर जल पंप की अनुशंसा करने की सबसे अधिक संभावना है।",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = NumberInputValidation(0, 10),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "2.15",
                question = "आप कब तक सौर जल पंप का उपयोग करने की उम्मीद करते हैं? (वर्ष में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "3.1",
                question = "कृषि एवं खाद्य का उपयोग (Agriculture and Food Impact)\n सौर जल पंप तकनीकी के प्रयोग से पूर्व \n\n सौर जल पंप के उपयोग करने से पहले आपकी कृषि भूमि कितने हेक्टेयर की थी? (हेक्टेयर में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "3.2",
                question = "सौर जल पंप के उपयोग के पहले आपके खेत में कौन-कौन सी फसलें उपजाई जाती थी?",
                options = listOf(
                    OptionType.Button("कृपया फसलें बताये", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController().navigate(
                                R.id.action_nav_home_to_cropProfileFragment,
                                bundleOf("formKey" to question.id)
                            )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button,
            ),
            Question(
                id = "3.6",
                question = "सौर जल पंप के प्रयोग के उपरांत \n\n अब आप कितने हेक्टेयर कृषि भूमि में खेती करते है ? (हेक्टेयर में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "3.7",
                question = "इस कुल हेक्टेयर के कितने हिस्से में आप सौर जल पंप से खेती कर रहे है ? (हेक्टेयर में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "3.8",
                question = "क्या आपने पहले के बनाम सौर जल पंप के प्रयोग कर खेती करने में फसलों में बदलाव किया है ? (हाँ/नहीं)",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "3.8.1",
                question = "सौर जल पंप के प्रयोग के उपरांत आपने कौन कौन सी फसलों की उपज प्रारंभ की है? सभी फसलों को सूचीबद्ध करें",
                options = listOf(
                    OptionType.Button("कृपया फसलें बताये", object : ButtonAction {
                        override fun doAction(view: View, question: Question) {
                            view.findNavController().navigate(
                                R.id.action_nav_home_to_financialProducts,
                                bundleOf("formKey" to question.id)
                            )
                        }
                    })
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.Button,
            ),
            Question(
                id = "3.15",
                question = "क्या पूर्व के सामान्य मौसम की तुलना में वर्षा एवं तापमान के बदलाव ने आपके कृषि पद्धतियों एवं फ़सल पैदावार को प्रभावित किया है ?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "3.15.1",
                question = "अगर हाँ, कृपया इस परिवर्तन एवं उसका आपके कृषि कार्य पर हुए प्रभाव की व्याख्या करे",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "4.1",
                question = "जल का उपयोग (Water Impact) \n\n सौर जल पंप के उपयोग से पूर्व  \n\n सौर जल पंप के उपयोग के पूर्व, फसलों की सिचाई/पानी पटाने के लिए क्या-क्या कार्य किया जाता था ?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "वर्षा जल"),
                            CheckBoxItems("2", "कुआं"),
                            CheckBoxItems("3", "ड्रिप/सूक्ष्म सिंचाई"),
                            CheckBoxItems("4", "पंप"),
                            CheckBoxItems("5", "फुहारा/छिरकाव सिंचाई"),
                            CheckBoxItems("6", "नाली/बाढ़ सिंचाई"),
                            CheckBoxItems("7", "उप-सिंचाई"),
                            CheckBoxItems("8", "भारी बाढ़ जल से सिंचाई"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "4.2",
                question = "उपरोक्त माध्यमों का प्रयोग कितने कृषि भूमि पर सिंचाई हेतु किया जाता था ? (हेक्टेयर में )",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.3",
                question = "पूर्व के सिंचाई माध्यमों का प्रयोग प्रतिवर्ष कितने महीने किया जाता था ? (माह/प्रतिवर्ष)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.4",
                question = "फसलों की सिंचाई हेतु प्रतिदिन कितने जल का उपयोग किया जाता था ? लीटर/प्रति दिन)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.5",
                question = "प्रतिमाह कितने दिन पूर्व के सिंचाई माध्यमों का प्रयोग आप फसलों की सिंचाई हेतु करते थे ? (प्रतिदिन/माह)-इस प्रश्न का उत्तर प्राप्त करने में सभी फसलों को सम्मिलित करे",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.6",
                question = "अलग-अलग मौसमों में इस कार्य का आपने अलग-अलग कितनी बार प्रयोग किया? (कुल संख्या/प्रति मौसम)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.6.1",
                question = "क्या सौर जल पंप के प्रयोग के पहले आपके परिवार की महिलाएं सिंचाई कार्य में सम्मिलित होती थी?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "4.7",
                question = "सौर जल पंप के उपयोग के उपरांत \n\n सौर जल पंप के उपयोग करने के बाद खेतों के सिंचाई हेतु अब क्या कार्य किये जाते है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "वर्षा जल"),
                            CheckBoxItems("2", "कुआँ"),
                            CheckBoxItems("3", "ड्रिप/सूक्ष्म सिंचाई"),
                            CheckBoxItems("4", "पंप (विद्युत/डीजल/सौर-प्रयोग में आने वाले सभी का चुनाव करे)"),
                            CheckBoxItems("5", "फुहारा/छिरकाव सिंचाई"),
                            CheckBoxItems("6", "नाली/बाढ़ सिंचाई"),
                            CheckBoxItems("7", "उप-सिंचाई"),
                            CheckBoxItems("8", "भारी बाढ़ जल से सिंचाई"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "4.8",
                question = "अब कितने कृषि भूमि पर सौर जल पंप का प्रयोग किया जाता है ? (हेक्टेयर में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.9",
                question = "क्या आप अभी भी इन्ही फसलों की सिंचाई के लिए अतिरिक्त माध्यमों का प्रयोग करते है?  (प्रयोग में आने वाले सभी का चुनाव करे)",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "वर्षा जल"),
                            CheckBoxItems("2", "कुआँ"),
                            CheckBoxItems("3", "ड्रिप/सूक्ष्म सिंचाई"),
                            CheckBoxItems("4", "पंप (विद्युत/डीजल/सौर-प्रयोग में आने वाले सभी का चुनाव करे)"),
                            CheckBoxItems("5", "फुहारा/छिरकाव सिंचाई"),
                            CheckBoxItems("6", "नाली/बाढ़ सिंचाई"),
                            CheckBoxItems("7", "उप-सिंचाई"),
                            CheckBoxItems("8", "भारी बाढ़ जल से सिंचाई"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.Switch,
            ),
            Question(
                id = "4.10",
                question = "प्रतिवर्ष कितने माह आप सौर जल पंप का प्रयोग सिंचाई हेतु करते है? (माह/प्रतिवर्ष)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.11",
                question = "प्रतिमाह कितने दिन आप सौर जल पंप का प्रयोग आप खेतों की सिंचाई हेतु करते है ? (दिन/प्रतिमाह)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.12",
                question = "सौर जल पंप का प्रयोग करते हुए फसलों की सिंचाई हेतु प्रतिदिन कितने जल का उपयोग आप करते है? (लीटर में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.13",
                question = "क्या वर्ष के विभिन्न मौसमों के दौरान आपके द्वारा सौर जल पंप का उपयोग करने की संख्या अलग-अलग है? (मौसम के अनुसार संख्या)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.14",
                question = "इस सौर जल पंप का उपयोग करने के दौरान क्या आपने फ़सल बदलने का निर्णय लिया है क्यूंकि आप नई तकनीकी का प्रयोग कर रहे है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "4.14.1",
                question = "यदि हां तो कृपया विस्तार से बताये",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
                isOptional = true
            ),
            Question(
                id = "4.15",
                question = "सौर जल पंप के अलावा क्या कोई ऐसा कारण जो आपके कृषि कार्य में कितना पानी का प्रयोग करेंगे, इस बात को प्रभावित करते है? उसकी सूची बनायेI",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT,
            ),
            Question(
                id = "4.16",
                question = "क्या महिलाएं सिंचाई कार्य में सम्मिलित होती है? (हाँ/नहीं)",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "4.17",
                question = "प्रतिवर्ष कितने माह आप सौर जल पंप के अलावे अन्य सिंचाई श्रोतो का प्रयोग करते है ?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.18",
                question = "प्रतिवर्ष कितने माह आप अन्य पंप या स्रोत का उपयोग सिंचाई के लिए करते है? (माह/प्रतिवर्ष)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.19",
                question = "प्रतिमाह कितने दिन अन्य श्रोतों का प्रयोग सिंचाई के लिए करते है ? (दिन/प्रतिमाह)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.20",
                question = "फसलों की सिंचाई के लिए अन्य श्रोतों से कितने जल का उपयोग प्रतिदिन आप करते है? (लीटर में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "4.21",
                question = "क्या वर्ष के विभिन्न मौसमों के दौरान आपके द्वारा सौर जल पंप का उपयोग करने की संख्या अलग-अलग है? (मौसम के अनुसार संख्या)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "5.1",
                question = "उर्जा का उपयोग (Energy Impact) \n\n सौर जल पंप के उपयोग के उपरांत \n\n आप किस कृषि गतिविधि/उद्देश्य के लिए सौर जल पंप का प्रयोग करते है?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "5.2",
                question = "आप कितने समय से सौर जल पंप का प्रयोग कर रहे है? (महीने में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "5.3",
                question = "आप अपने सिंचाई यंत्र को खेत तक कैसे ले जाते है?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "5.4",
                question = "क्या सिंचाई यत्र को खेत तक ले जाने में कोई परिवहन लागत शामिल है? (लागत रुपये में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "5.4.1",
                question = "किलोवाट में पैनल का माप क्या है?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "5.4.2",
                question = "जल पंप का माप क्या है? (हॉर्सपावर में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "5.4.3",
                question = "पंप के जल बहाव का माप क्या है ? (लीटर/प्रति मिनट)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.INPUT
            ),
            Question(
                id = "5.5",
                question = "सौर जल पंप के उपयोग के पूर्व \n\n सामान कृषि गतिविधि/उद्देश्य के लिए सौर जल पंप के उपयोग के पूर्व किस उर्जा स्रोत का उपयोग किया जाता था?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "प्रत्यक्ष ईंधन खपत"),
                            CheckBoxItems("2", "ग्रिड विद्युत"),
                            CheckBoxItems("3", "जेनेरेटर की बिजली"),
                            CheckBoxItems("4", "कोई नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "5.6",
                question = "अगर प्रत्यक्ष ईंधन खपत, किस प्रकार के ईंधन का पूर्व में प्रयोग किया जाता था?",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_TEXT, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.6.1",
                question = "प्रत्येक बार आप कितने ईंधन का उपयोग करते है ? (लीटर में)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.6.2",
                question = "आप इस ईंधन का कितनी बार उपयोग करते है? (दिन/प्रतिवर्ष)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.7",
                question = "अगर ग्रिड विद्युत् का उपयोग, \n\n क्या बिजली केन्द्रीय विद्युत स्टेशन से ली गयी या छोटे पैमाने की आवासीय वोद्युत प्रणाली से?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "केन्द्रीय विद्युत स्टेशन"),
                            CheckBoxItems("2", "छोटे पैमाने की आवासीय वोद्युत प्रणाली"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.7.1",
                question = "प्रतिदिन कितनी बिजली की खपत की गयी? (किलोवाट/प्रतिघंटा)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.7.2",
                question = "आप बिजली का कितनी बार उपयोग करते है? (दिन/प्रतिवर्ष)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.8",
                question = "अगर जेनेरेटर के माध्यम से प्राप्त विद्युत् का उपयोग \n\n प्रत्येक बार जेनेरेटर के उपयोग से कुल कितना विद्युत् का उत्पादन करते है? (किलोवाट/प्रति घंटा)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.8.1",
                question = "आप जेनेरेटर का कितनी बार उपयोग करते है? (दिन/प्रतिवर्ष)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.8.2",
                question = "जेनेरेटर के प्रयोग में किस प्रकार का ईंधन का उपयोग करते है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "विमानन तेल"),
                            CheckBoxItems("2", "कच्चा तेल/डीजल"),
                            CheckBoxItems("3", "ईंधन तेल (अवशिष्ट)"),
                            CheckBoxItems("4", "किरासन"),
                            CheckBoxItems("5", "तरलीकृत पेट्रोलियम गैस (एलपीजी)"),
                            CheckBoxItems("6", "लुब्रीकेंट"),
                            CheckBoxItems("7", "मोटर गैस (पेट्रोल)"),
                            CheckBoxItems("8", "नेफ्था"),
                            CheckBoxItems("9", "प्राकृतिक तरल गैस"),
                            CheckBoxItems("10", "शेल तेल"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
            Question(
                id = "5.8.3",
                question = "आपका जेनेरेटर प्रतिवर्ष कितना ईंधन उपयोग करता है (ईंधन)",
                options = listOf(
                    OptionType.InputField(InputType.TYPE_CLASS_NUMBER, "कृपया उल्लेख करे")
                ),
                validate = StringInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
                isOptional = true
            ),
            Question(
                id = "5.8.4",
                question = "क्या पूर्व में इस्तेमाल किये गए जेनेरेटर के उपयोग के लिए कोई लोंग पुस्तिका या रिकॉर्ड रखा गया था? यदि हाँ, तो क्या आप यह जानकारी साँझा कर सकते है?",
                options = listOf(
                    OptionType.CheckBox(
                        listOf(
                            CheckBoxItems("1", "हाँ"),
                            CheckBoxItems("2", "नहीं"),
                        )
                    )
                ),
                validate = CheckboxInputValidation(),
                optionType = OptionTypeEnum.CHECK_BOX,
            ),
        )
    }

    suspend fun insertData(answerEntity: AnswerEntity) =
        withContext(Dispatchers.IO) { answersDao.insertAnswer(answerEntity) }

}
