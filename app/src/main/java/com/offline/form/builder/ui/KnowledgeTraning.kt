package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class KnowledgeTraning : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitI4Data(arguments?.getString("formKey", "") ?: "", userdataList)
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Particulars",
                choices = listOf(
                    "Planting material",
                    "Planting method",
                    "Nutrient management",
                    "Disease and pest management",
                    "Canopy management",
                    "Harvesting practices",
                    "Post-harvest management",
                    "Marketing management",
                    "Any other (Please specify)",
                ),
                hint = "please enter ",
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "I.4.1 Change in practice (Before and after training)",
                hint = "please enter ",
                singleLineTextType = SingleLineTextType.TEXT,
                errorMessage = "Please provide an answer"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_CHOICE,
                question = "I.4.2 Benefits received by the adoption of training",
                hint = "please enter ",
                choices = listOf(
                    "1 = Reduced cost of production",
                    "2 = Improved yield of cashew",
                    "3 = Improved quality of produce",
                    "4 = realised better market prices",
                    "5 = Got better access to market",
                    "6 = any other (please specify)"
                ),
                errorMessage = "Please provide an answer"
            )
        )
        return forms
    }
}