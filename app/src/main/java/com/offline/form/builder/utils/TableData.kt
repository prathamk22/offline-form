package com.offline.form.builder.utils

import com.pradeep.form.simple_form.model.Form

data class TableData(
    val sheetName: String,
    val formAns: List<Form>,
    val columnNames: List<String>
)

object Constants{
    const val USERS_SHEET = "Users Sheet"
    const val HOUSE_HOLD_ASSETS_SHEET = "House hold assets sheet"
}