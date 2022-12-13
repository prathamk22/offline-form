package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class FarmerGroupAssociation : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitLData(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Group name",
                choices = listOf(
                    "Producer's association",
                    "Cooperatives",
                    "Farmer's savings club",
                    "Farmer's marketing group",
                    "Farmer's credit group",
                    "Other's farmers group"
                ),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Are you a member",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = "Is the group actively working?",
                choices = listOf("1. Yes", "2. No"),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.NUMBER,
                question = "Period of membership (Years)",
                hint = "please enter your membership",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }
}