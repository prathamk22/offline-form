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
                isMandatory = false,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Particular name",
                choices = listOf(
                    "1 Irrigation infrastructure for cashew nurseries and gardens",
                    "2 Cashew Plantation Rejuvenation and Establishment",
                    "3 Infrastructure for Agro-processing and Marketing",
                    "4 Capacity building and training",
                ),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Particular type",
                choices = listOf(
                    "1.1 Rehabilitation of feeder roads",
                    "1.2 Establishment of main nursery at ZARI-Simulumbe",
                    "1.3 Establishment of Clone Gardens",
                    "1.4 Establishment of Public Demonstration Nurseries",
                    "1.5 Establishment of Community Nurseries",
                    "2.1 Canopy re-establishment",
                    "2.2 Rehabilitation of existing plantations",
                    "2.3 Replanting bare areas (within old areas)",
                    "2.4 Establishment of new Farm",
                    "3.1 Construction of cashew processing factory",
                    "3.2 Construction of community collection / shorting sheds",
                    "3.3 Construction of cashew bulking facilities",
                    "3.4 Construction of District cashew Cluster Centre",
                    "4.1 Community awareness/mobilisation",
                    "4.2 Staff / Farmers / Cashew Processors Training",
                    "4.3 Institutional support",
                ),
                errorMessage = "Please choose"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
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
                isMandatory = false,
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
                question = "Explain how what and how much benefits have occurred",
                hint = "Please enter your explanation",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }
}