package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class SourcesOfIncome : NewBaseTableFragment() {

    private val columnNameQuestionMap = hashMapOf(
        "Source (√)" to "What were the sources of your household’s food/income over the last 12 months?",
        "Rank (#)" to "Rank these sources based on the proportion of food/income they provide for the household. (In column b, indicate 1 for the source that provided the most food/income in the last 12 months, 2 for the source that provides the second most food/income…and so on until the number of sources identified in Q1 is reached).",
        "Seasonality" to "Which of these food/income sources are seasonal and which season? (indicate with D for dry season and W for wet season, or Y for year-round in column c).",
        "Stress (√)" to "Which are sources that you only rely on during times of stress?",
        "Change" to "Over the past 12 months, have you changed any source and their relative importance due to a shock?",
    )

    override fun getSheetName() = getTableKey()

    override fun getColumnNames() = columnNameQuestionMap.map { it.key }

    override fun getSection1FormData(): List<Form> {
        return mutableListOf<Form>().apply {
            for (columnName in columnNameQuestionMap) {
                add(
                    Form(
                        isMandatory = true,
                        formType = FormTypes.SINGLE_LINE_TEXT,
                        question = columnName.value,
                        hint = "Enter $columnName",
                        errorMessage = "Please choose"
                    )
                )
            }
        }
    }
}