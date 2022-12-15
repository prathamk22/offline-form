package com.offline.form.builder.ui.gallery

import com.offline.form.builder.ui.BaseTableFragment
import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class AddPeopleFragment : BaseTableFragment() {
    override fun onSubmitListCalled() {
        viewModel.submitData(arguments?.getString("formKey", "") ?: "", userdataList)

    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Name of the household member",
                hint = "please enter your name",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Gender",
                choices = listOf("1. Male", "2. Female"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,

                question = "Age",
                hint = "in yrs",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer",

            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Education Level",
                choices = listOf(
                    "1 = No Formal Education",
                    "2 = Less than Grade 5",
                    "3 = Less than grade 10",
                    "4 = Less than Grade 12",
                    "5 = College Student",
                    "6 = University Undergraduate Student",
                    "7 = Tertiary Certificate, Diploma",
                    "8= Bachelors Degree",
                    "9 = Masters Degree and Above.",
                ),
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Actively involved in Farming",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Earning member of HH",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Suffered from COVID-19 at any time",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Suffered from HIV AIDS",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Suffered from any other Health issue",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        return forms
    }

}