package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class FoodDayMeal : NewBaseTableFragment() {

    override fun getSheetName() = "Food Meal"

    override fun getColumnNames() = listOf(
        "BREAKFAST",
        "SNACK",
        "LUNCH",
        "SNACK",
        "DINNER",
        "SNACK",
    )

    override fun getSection1FormData(): List<Form> {
        return getColumnNames().map {
            Form(
                isMandatory = true,
                formType = FormTypes.MULTI_LINE_TEXT,
                question = it,
                hint = "Enter your $it",
                errorMessage = "Please choose"
            )
        }
    }
}
