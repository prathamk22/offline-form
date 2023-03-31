package com.offline.form.builder.ui

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.offline.form.builder.utils.TableData
import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class LoanBorrowingData : NewBaseTableFragment() {

    override fun getSheetName() = "Loan Borrowing Data"

    override fun getColumnNames(): List<String> {
        return listOf(
            "Serial Number",
            "Source of Credit* (Code)",
            "Scheme name",
            "Loan Codes"
        )
    }

    override fun getSection1FormData(): List<Form> {
        val forms = mutableListOf<Form>()
        val houseMembers = viewModel.getAnsIfAvailable("S1")
        if (houseMembers.isNullOrEmpty()){
            Toast.makeText(requireContext(), "No family members are added right now", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
            return emptyList()
        }
        val typeToken = object : TypeToken<List<TableData>>() {}.type
        val tableList = Gson().fromJson<List<TableData>>(houseMembers, typeToken)
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_CHOICE,
                question = "Select from the members list",
                choices = tableList.mapIndexed { i, item ->
                    item.formAns.getOrNull(i)?.answer ?: ""
                },
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Serial Number",
                hint = "Please enter Serial Number",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_CHOICE,
                choices = listOf(
                    "Government ",
                    "Co-operative society/bank ",
                    "Commercial bank incl. regional rural bank ",
                    "Insurance ",
                    "Provident fund ",
                    "Financial corporation/institution",
                ),
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_LINE_TEXT,
                choices = listOf(
                    "Capital expenditure in farm business",
                    "Current expenditure in farm business",
                    "Capital expenditure in non-farm business",
                    "Current expenditure in non-farm business",
                    "Expenditure on litigation",
                    "Repayment of debt",
                    "Financial investment expenditure",
                    "For education",
                    "For medical treatment",
                    "For housing",
                    "For other household expenditure",
                    "Others",
                ),
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "If others please specify",
                hint = "",
                errorMessage = "Please enter proper value"
            )
        )
        forms.add(
            Form(
                isMandatory = false,
                formType = FormTypes.SINGLE_LINE_TEXT,
                question = "Purpose of Loan Codes",
                hint = "Please enter Purpose of Loan Codes",
                errorMessage = "Please enter proper value"
            )
        )
        return forms
    }

}
