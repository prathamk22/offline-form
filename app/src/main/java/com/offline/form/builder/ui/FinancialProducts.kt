package com.offline.form.builder.ui

import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.form_items.SingleLineTextType
import com.pradeep.form.simple_form.model.Form

class FinancialProducts : NewBaseTableFragment() {

    override fun getSheetName() = getTableKey()

    override fun getColumnNames(): List<String> {
        return listOf(
            "Agency Banking Booths",
            "Insurance products for Savings Groups",
            "Micro- investment loans",
            "Savings Group Accounts",
            "Livestock financing product",
            "Mobile Money Accounts",
            "Savings Group loan product",
            "Self Help Group Accounts",
            "Agro-Dealer Agency points",
            "Digital Payment Services (various)",
            "Money transfers, cash in, cash out",
            "Digital Savings Accounts",
            "Digital Loan disbursements",
            "Digital Loan repayments",
            "Lay-by input facility, Paygo",
            "Mobile App (various capabilities)",
            "Invoice discount for Farmers",
            "Digitized Savings Groups",
            "e-Wallet accounts",
            "Point of Sale machines (PoS)",
        )
    }

    override fun getSection1FormData(): List<Form> {
        return mutableListOf<Form>().apply {
            getColumnNames().forEachIndexed { index, s ->
                add(
                    Form(
                        isMandatory = false,
                        formType = FormTypes.SINGLE_CHOICE,
                        question = s,
                        choices = listOf(
                            "YES", "NO"
                        ),
                        singleLineTextType = SingleLineTextType.TEXT,
                        errorMessage = "Please choose"
                    )
                )
            }
        }
    }

}