package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class FinancialProducts : NewBaseTableFragment() {

    override fun getSheetName() = getTableKey()

    override fun getColumnNames(): List<String> {
        return listOf(
            "3.8.1",
            "3.9",
            "3.10",
            "3.11",
            "3.12",
            "3.13",
            "3.14",
        )
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "3.8.1 सौर जल पंप के प्रयोग के उपरांत आपने कौन कौन सी फसलों की उपज प्रारंभ की है?",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.9 आप कितने हेक्टेयर खेत में (फ़सल-क) उपजाते है? (हेक्टेयर में)",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.10 एक साल में (फ़सल-क) के लिए कितना फ़सल मौसम है?",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.11 प्रत्येक फ़सल मौसम में (फ़सल क) का सामान्य उपज क्या है ? (किलोग्राम में)",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.12 पिछले वर्ष में (फ़सल क) का कुल उपज कितना था ? (किलोग्राम में)",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.13 इस फ़सल के कुल उपज में से औसतन कितने उपज की बिक्री प्रतिवर्ष आप करते है ? (किलोग्राम में)",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.14 इस फ़सल का औसत विक्रय मूल्य क्या है ? (भारतीय रुपये/प्रति किलोग्राम)",
                errorMessage = "Please enter proper value"
            )
        )
        return forms
    }

}