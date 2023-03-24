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
                question = "Full name",
                hint = "Please enter full name of the user",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Relation to Head of the Household",
                hint = "please enter Relation to Head of the Household",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Sex",
                choices = listOf(
                    "Male",
                    "Female"
                ),
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "How old was (NAME) on his/her last birthday?",
                hint = "Please enter the age of the user",
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "How old was (NAME) on his/her last birthday?",
                hint = "Please enter the age of the user",
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Can he/she read a newspaper or letter?",
                choices = listOf(
                    "EASILY",
                    "WITH DIFFICULTY",
                    "NOT AT ALL",
                    "DON’T KNOW",
                    "OR NOT APPLICABLE",
                ),
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Is he/she attending school?",
                choices = listOf(
                    "YES",
                    "NO",
                ),
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }

}