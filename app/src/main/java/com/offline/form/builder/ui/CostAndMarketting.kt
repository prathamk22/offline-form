package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class CostAndMarketting : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitJ10Data(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Particular name",
                choices = listOf(
                    "Transportation (from Farm to Market)",
                    "Packaging",
                    "Loading & Offloading",
                    "Marketing fee, if any",
                    "Trader's commision, if any",
                    "Any other's if any"
                ),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "Cost (Kwacha per KG)",
                hint = "please enter your membership",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Change in cost over last 5 years",
                choices = listOf(
                    "1. Increased",
                    "2. Decreased",
                    "3. No charges",
                ),
                errorMessage = "Please choose"
            )
        )
        return forms
    }
}