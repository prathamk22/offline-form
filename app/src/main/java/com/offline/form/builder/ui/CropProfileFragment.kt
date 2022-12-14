package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class CropProfileFragment : BaseTableFragment() {
    override fun onSubmitListCalled() {
        viewModel.submitFData(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "F1 Crop Period",
                choices = listOf(
                    "Crop Period 1 1",
                    "Crop Period 2",
                    "Plantation crop(throughout year)",
                ),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "F2 Crop Name",
                hint = "please enter Crop Name",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "F3 Area (in Ha)",
                hint = "please enter your membership",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "F4 Total Production (in Kg) ",
                hint = "please enter Total Production (in Kg)",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "F5 Marketable surplus (in % of total production)  ",
                hint = "please enter Marketable surplus",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "F6 Selling price (Kwacha per Kg)",
                hint = "please enter Selling price",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "F7 Quantity sold ",
                hint = "please enter Quantity sold ",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }
}