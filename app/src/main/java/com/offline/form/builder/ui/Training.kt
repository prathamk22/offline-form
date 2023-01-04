package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class Training : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitI3Data(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "I.3.1 Title / Topic of the training",
                hint = "please enter ",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "I.3.2 Number of trainings",
                hint = "please enter ",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "I.3.3 Name of Training Institute/Department",
                hint = "please enter name of Training Institute/Department",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "I.3.4 How will you rate the usefulness of training on a scale of 1 to 5 (1 for poor and 5 for best)",
                hint = "please enter ",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }

}