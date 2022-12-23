package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class RunOutFood : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitC3aData(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        return listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December").map {
            Form(
                formType = FormTypes.SINGLE_CHOICE,
                question = it,
                choices = listOf(
                    "Yes",
                    "No",
                ),
                errorMessage = "Please choose"
            )
        }
    }
}
