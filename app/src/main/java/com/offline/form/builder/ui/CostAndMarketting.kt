package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class CostAndMarketting : NewBaseTableFragment() {
    override fun getSheetName() = "1.4"

    override fun getColumnNames(): List<String> {
        return listOf(
            "1.4.1",
            "1.4.2",
            "1.4.3",
            "1.4.4",
        )
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "क्या आय का यह स्रोत किसी तरह से नवाचार पर निर्भर करता है या उसका उपयोग करता है?",
                choices = listOf("हाँ", "नहीं"),
                errorMessage = "कृपया हाँ/नहीं बताये"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "यह अतिरिक्त कार्य क्या है और यह कार्य कौन करता है ?",
                errorMessage = "कृपया बताये"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.NUMBER,
                question = "आपके परिवार का वह व्यक्ति कितने समय से यह काम कर रहा है? (महीने में )",
                errorMessage = "कृपया महीने में बताये"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.NUMBER,
                question = "इस अतिरिक्त कार्य से आपका परिवार साल में कितना कमा लेता है",
                errorMessage = "कृपया बताये"
            )
        )
        return forms
    }
}