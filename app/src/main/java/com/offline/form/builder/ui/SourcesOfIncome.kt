package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class SourcesOfIncome : NewBaseTableFragment() {

    override fun getSheetName() = getTableKey()

    override fun getColumnNames() = listOf(
        "Source (√)",
        "Rank (#)",
        "Seasonality",
        "Stress (√)",
        "Change",
    )

    override fun getSection1FormData(): List<Form> {
        return mutableListOf<Form>().apply {
            for (columnName in getColumnNames()) {
                add(
                    Form(
                        isMandatory = true,
                        formType = FormTypes.SINGLE_LINE_TEXT,
                        question = columnName,
                        hint = "Enter $columnName",
                        errorMessage = "Please choose"
                    )
                )
            }
        }
    }
}