package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class MicrofinanceExperience : NewBaseTableFragment() {

    override fun getSheetName() = "Micro finance details"

    override fun getColumnNames(): List<String> {
        return listOf(
            "Serial Number",
            "Name of the Organization",
            "Type of Group",
            "Number of members",
            "Frequency of meetings",
            "Loan availed"
        )
    }

    override fun getSection1FormData(): List<Form> {
        return mutableListOf<Form>().apply {
            add(
                Form(
                    isMandatory = true,
                    formType = FormTypes.SINGLE_LINE_TEXT,
                    question = "Serial Number (Number of members in the household)",
                    hint = "please enter Serial Number",
                    singleLineTextType = SingleLineTextType.TEXT,
                    errorMessage = "Please choose"
                )
            )
            add(
                Form(
                    isMandatory = true,
                    formType = FormTypes.SINGLE_LINE_TEXT,
                    question = "Name of the Organization providing Microfinance",
                    hint = "please enter Name of the Organization",
                    singleLineTextType = SingleLineTextType.TEXT,
                    errorMessage = "Please choose"
                )
            )
            add(
                Form(
                    isMandatory = true,
                    formType = FormTypes.SINGLE_CHOICE,
                    question = "Type of Group",
                    choices = listOf(
                        "SHG", "JLG", "Livelihood collective"
                    ),
                    singleLineTextType = SingleLineTextType.TEXT,
                    errorMessage = "Please choose"
                )
            )
            add(
                Form(
                    isMandatory = true,
                    formType = FormTypes.NUMBER,
                    question = "Number of members in the group",
                    hint = "please enter Number of members",
                    singleLineTextType = SingleLineTextType.TEXT,
                    errorMessage = "Please choose"
                )
            )
            add(
                Form(
                    isMandatory = true,
                    formType = FormTypes.NUMBER,
                    question = "Frequency of meetings",
                    hint = "please enter Frequency of meetings",
                    singleLineTextType = SingleLineTextType.TEXT,
                    errorMessage = "Please choose"
                )
            )
            add(
                Form(
                    isMandatory = true,
                    formType = FormTypes.SINGLE_CHOICE,
                    question = "Loan availed",
                    choices = listOf(
                        "YES", "NO"
                    ),
                    singleLineTextType = SingleLineTextType.TEXT,
                    errorMessage = "Please choose"
                )
            )
        }
    }
}