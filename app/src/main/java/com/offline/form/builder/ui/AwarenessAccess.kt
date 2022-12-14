package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class AwarenessAccess : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitG2Data(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Particular name",
                choices = listOf(
                    "Irrigation infrastructure for cashew nurseries and gardens",
                    "Rehabilitation of feeder roads",
                    "Establishment of main nursery at ZARI-Simulumbe",
                    "Establishment of Clone Gardens",
                    "Establishment of Public Demonstration Nurseries",
                    "Establishment of Community Nurseries",
                    "Cashew Plantation Rejuvenation and Establishment",
                    "Canopy re-establishment",
                    "Rehabilitation of existing plantations",
                    "Replanting bare areas (within old areas)",
                    "Establishment of new Farm",
                    "Infrastructure for Agro-processing and Marketing",
                    "Construction of cashew processing factory",
                    "Construction of community collection / shorting sheds",
                    "Construction of cashew bulking facilities",
                    "Construction of District cashew Cluster Centre",
                    "Capacity building and training",
                    "Community awareness/mobilisation",
                    "Staff / Farmers / Cashew Processors Training",
                    "Institutional support",
                ),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "G.2.a Aware of component",
                choices = listOf(
                    "1. Yes",
                    "2. No",
                ),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = true,
                formType = FormTypes.SINGLE_CHOICE,
                question = "G.2.b Accessed / Availed benefit",
                choices = listOf(
                    "1. Yes",
                    "2. No",
                ),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Explain how what and how much benefits have accrued",
                hint = "please enter your explaination",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }
}