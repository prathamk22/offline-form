package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class CashewProduction : NewBaseTableFragment() {
    override fun getSheetName() = "Season food items"

    override fun getColumnNames(): List<String> {
        return listOf(
            "Farming/Crop production and sales",
            "Livestock production and sales",
            "Wage labour (local)",
            "Fishing",
            "Salaried work, including in-kind payment",
            "Sale of wild/bush products (incl charcoal)",
            "Other self-employment/own business",
            "Sale of land/other non-livestock assets",
            "Wild foods for household consumption",
            "Mining on community land",
            "Barter trade",
            "Remittances",
            "Gifts/inheritance",
            "Relief/Donations (church assistance, NGOs, etc.)",
            "Borrowing",
            "Bank interests/treasury bills",
            "Pension funds",
            "Social security",
            "Government transfers (e.g., FISP/ social cash transfer)",
            "Share-out from Saving Group",
        )
    }

    override fun getSection1FormData(): List<Form> {
        return getColumnNames().map {
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "$it (Enter D for dry season and W for wet season, or Y for year-round)",
                hint = "Enter the proper value for $it",
                errorMessage = "Please enter proper rank"
            )
        }
    }
}