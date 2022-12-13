package com.offline.form.builder.utils

import com.pradeep.form.simple_form.model.Form

data class TableData(
    val sheetName: String,
    val formAns: List<Form>,
    val columnNames: List<String>
)

object Constants{
    const val CROP_PROFILE: String = "Crop Profile"
    const val USERS_SHEET = "Users Sheet"
    const val HOUSE_HOLD_ASSETS_SHEET = "House hold assets sheet"
    const val FARMERS_ASSOCAITION = "Farmers association"
    const val CASHEW_COST_MARKETING = "Cost and Marketing of cashew"
}