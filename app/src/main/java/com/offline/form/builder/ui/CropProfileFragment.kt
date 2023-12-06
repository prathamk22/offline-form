package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class CropProfileFragment : NewBaseTableFragment() {

    override fun getSheetName() = getTableKey()

    override fun getColumnNames(): List<String> {
        return arrayListOf(
            "3.2",
            "3.3",
            "3.4",
            "3.5",
            "3.5.1",
        )
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "3.2 सौर जल पंप के उपयोग के पहले आपके खेत में कौन-कौन सी फसलें उपजाई जाती थी?",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.3 आप कितने हेक्टेयर खेत में (फ़सल-क) उपजाते थे ? (हेक्टेयर में)",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.4 एक साल में (फ़सल-क)  के लिए कितना फ़सल मौसम था?",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.5 प्रत्येक फ़सल मौसम में (फ़सल क) का सामान्य उपज क्या था ? (किलोग्राम में)",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "3.5.1 इस फ़सल का औसत विक्रय मूल्य /प्रति किलोग्राम कितना था ? (भारतीय रुपये/प्रति किलोग्राम  में)",
                errorMessage = "Please enter proper value"
            )
        )
        return forms
    }

}