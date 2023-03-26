package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class RiskUncertainities : NewBaseTableFragment() {

    override fun getSheetName() = "Risk Uncertainities"

    override fun getColumnNames() = listOf(
        "11.1.1",
        "11.1.2",
        "11.1.3",
        "11.1.4",
        "11.1.5",
        "11.1.6",
        "11.1.7",
        "11.1.8",
        "11.1.9",
    )

    override fun getSection1FormData(): List<Form> {
        return mutableListOf<Form>().apply {
            listOf(
                "Crop failure due to excessive rainfall, very low rainfall or unseasonal rainfall",
                "Sudden decline in productivity of crops due to pest infestation, etc.",
                "Sudden fall in market prices of crops",
                "Loss of livestock due to flood, disease, etc.",
                "Death of the earning member of the household",
                "Sudden health problems/ accident",
                "Sudden job loss",
                "Fire/ theft/ robbery",
            ).forEach {
                add(
                    Form(
                        isMandatory = true,
                        formType = FormTypes.SINGLE_CHOICE,
                        question = it,
                        choices = listOf(
                            "YES",
                            "NO"
                        ),
                        errorMessage = "Please choose"
                    )
                )
            }
            add(
                Form(
                    isMandatory = false,
                    formType = FormTypes.SINGLE_LINE_TEXT,
                    question = "Any other (specify)……",
                    hint = "Any other (specify)……",
                    errorMessage = "Please choose"
                )
            )
        }
    }
}
