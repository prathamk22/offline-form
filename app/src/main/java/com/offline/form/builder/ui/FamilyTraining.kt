package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class FamilyTraining : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitI2Data(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "Training Received by Male",
                hint = "please enter your training",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.NUMBER,
                question = "Training Received by Female",
                hint = "please enter your training",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }
}
