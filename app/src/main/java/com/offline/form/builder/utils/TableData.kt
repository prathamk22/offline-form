package com.offline.form.builder.utils

import com.pradeep.form.simple_form.model.Form

data class TableData(
    val sheetName: String,
    val formAns: List<Form>,
    val columnNames: List<String>
)

object Constants{
    const val I3_TRAINING: String = "I3 Traning"
    const val CASHEW_PRODUCTION: String = "H1 Cashew Production"
    const val AWARENESS_ACCESS_CIDP: String = "G2 Awareness of and Access to CIDP services"
    const val CROP_PROFILE: String = "F Crop Profile"
    const val USERS_SHEET = "Users Sheet"
    const val HOUSE_HOLD_ASSETS_SHEET = "C6 House hold assets sheet"
    const val FARMERS_ASSOCAITION = "L Farmers association"
    const val CASHEW_COST_MARKETING = "J10 Cost and Marketing of cashew"
}