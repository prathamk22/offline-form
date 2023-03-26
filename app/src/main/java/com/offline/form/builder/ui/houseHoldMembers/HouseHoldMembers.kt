package com.offline.form.builder.ui.houseHoldMembers

import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.offline.form.builder.ui.NewBaseTableFragment
import com.offline.form.builder.utils.TableData
import com.pradeep.form.simple_form.form_items.FormTypes
import com.pradeep.form.simple_form.model.Form

class HouseHoldMembers : NewBaseTableFragment() {

    override fun getSheetName() = "Sheet_${getTableKey()}"

    override fun getColumnNames(): List<String> {
        return listOf(getTableKey())
    }

    override fun validateAndSubmit(): Boolean {
        val maxCount = arguments?.getInt("count", 1) ?: 1
        return maxCount > (userdataList.getOrNull(0)?.getOrNull(0)?.answers?.size ?: 0)
    }

    override fun getSection1FormData(): List<Form> {
        val houseMembers = viewModel.getAnsIfAvailable("S1")
        if (houseMembers.isNullOrEmpty()){
            Toast.makeText(requireContext(), "No family members are added right now", Toast.LENGTH_LONG).show()
            return emptyList()
        }
        val typeToken = object : TypeToken<List<TableData>>() {}.type
        val tableList = Gson().fromJson<List<TableData>>(houseMembers, typeToken)
        val forms = mutableListOf<Form>()
        val maxCount = arguments?.getInt("count", 1) ?: 1
        forms.add(
            Form(
                isMandatory = true,
                formType = if (maxCount == 1) FormTypes.SINGLE_CHOICE else FormTypes.MULTI_CHOICE,
                question = "Select from the members list",
                choices = tableList.mapIndexed { i, item ->
                    item.formAns.getOrNull(i)?.answer ?: ""
                },
                errorMessage = "Please enter proper value"
            )
        )
        return forms
    }

}
