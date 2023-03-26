package com.offline.form.builder.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.offline.form.builder.data.db.AnswerEntity
import com.offline.form.builder.utils.*
import com.pradeep.form.simple_form.model.Form
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: HomeRepository = HomeRepository()
) : ViewModel() {

    private val answers = mutableMapOf<String, Any>()

    val isEnabled = MutableLiveData(false)

    val errorText = MutableLiveData<String?>(null)

    fun valueEntered(key: String, value: String) {
        Log.e("TAG", "valueEntered: Value is here $key $value")
        answers[key] = value
        checkAndUpdateButton()
    }

    private fun checkAndUpdateButton() {
        viewModelScope.launch(Dispatchers.Default) {
            repo.questions.forEach {
                if (!it.isOptional && answers[it.id] == null) {
                    errorText.postValue("Please fill ${it.id} question to enable submit button ")
                    isEnabled.postValue(false)
                    return@launch
                }
            }
            errorText.postValue(null)
            isEnabled.postValue(true)
        }
    }

    fun getAnsIfAvailable(key: String): String? = answers[key]?.toString()

    fun clearValue(key: String) {
        Log.e("TAG", "clearValue: value removed $key")
        answers.remove(key)
        checkAndUpdateButton()
    }

    fun insertData() {
        viewModelScope.launch {
            val answerEntity = AnswerEntity(
                nameOfRespondent = answers["A1"]?.toString() ?: "",
                data = Gson().toJson(
                    repo.questions.associate {
                        it.id to if (answers[it.id] != null) answers[it.id] else "NA"
                    }.toMutableMap().run {
                        put("Date", getCurrentDateTime().toString("dd/MM/yyyy"))
                        this
                    }),
                createdAt = System.currentTimeMillis()
            )
            repo.insertData(answerEntity)
            answers.clear()
        }
    }

    fun submitData(key: String, forms: List<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.USERS_SHEET,
                    it,
                    columnNames = listOf(
                        "Full name",
                        "Relation to Head of the Household",
                        "Sex",
                        "Age",
                        "LITERACY",
                        "SCHOOLING",
                        "Is he/she attending school?",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitC6Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.HOUSE_HOLD_ASSETS_SHEET,
                    it,
                    columnNames = listOf(
                        "C6.1",
                        "C6.2",
                        "C6.3",
                        "C6.4",
                        "C6.5",
                        "C6.6",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitLData(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.FARMERS_ASSOCAITION,
                    it,
                    columnNames = listOf(
                        "L1",
                        "L2",
                        "L3",
                        "L4",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitJ10Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.CASHEW_COST_MARKETING,
                    it,
                    columnNames = listOf(
                        "J10.0",
                        "J10.1",
                        "J10.2",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitFData(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.CROP_PROFILE,
                    it,
                    columnNames = listOf(
                        "F1",
                        "F2",
                        "F3",
                        "F4",
                        "F5",
                        "F6",
                        "F7",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitG2Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.AWARENESS_ACCESS_CIDP,
                    it,
                    columnNames = listOf(
                        "Project Intervention",
                        "Project Intervention Type",
                        "G.2.a",
                        "G.2.b",
                        "G.2.c",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitH1Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.CASHEW_PRODUCTION,
                    it,
                    columnNames = listOf(
                        "Particular",
                        "Yr. 2015",
                        "Yr. 2016",
                        "Yr. 2017",
                        "Yr. 2018",
                        "Yr. 2019",
                        "Yr. 2020",
                        "Yr. 2021",
                        "Yr. 2022",
                        "Total",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitI3Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.I3_TRAINING,
                    it,
                    columnNames = listOf(
                        "I.3.1",
                        "I.3.2",
                        "I.3.3",
                        "I.3.4",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun submitI4Data(key: String, forms: MutableList<List<Form>>) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    Constants.I4_KNOWLEDGE_TRANING,
                    it,
                    columnNames = listOf(
                        "I.4.0",
                        "I.4.1",
                        "I.4.2",
                    )
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

    fun valueEnteredInCheckbox(key: String, value: String, checked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val array = answers[key]
            val list = if (array == null || array.toString().isEmpty()) {
                mutableListOf()
            } else {
                val type = object : TypeToken<List<String>>() {}.type
                Gson().fromJson<List<String>>(array.toString(), type).toMutableList()
            }
            Log.e("TAG", "valueEnteredInCheckbox: $checked $value")
            if (checked) {
                if (!list.contains(value)) {
                    list.add(value)
                }
            } else {
                if (list.contains(value)) {
                    list.remove(value)
                }
            }

            if (list.isEmpty()) {
                Log.e("TAG", "valueEntered: Value removed from here $key $list")
                answers.remove(key)
            } else {
                Log.e("TAG", "valueEntered: Value is here $key $list")
                answers[key] = Gson().toJson(list)
            }
            checkAndUpdateButton()
        }
    }

    fun getAnsForCheckboxIfAvailable(key: String, value: String): Boolean {
        val array = answers[key]
        val list = if (array == null || array.toString().isEmpty()) {
            mutableListOf()
        } else {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson<List<String>>(array.toString(), type).toMutableList()
        }
        return list.contains(value)
    }

    fun submitDataWithSheetAndColumn(
        key: String,
        forms: MutableList<List<Form>>,
        sheetName: String,
        columnNames: List<String>
    ) {
        if (key.isEmpty())
            return
        viewModelScope.launch {
            val tableData = forms.map {
                TableData(
                    sheetName,
                    it,
                    columnNames = columnNames
                )
            }
            val data = Gson().toJson(tableData)
            Log.e("TAG", "submitData: $key $data")
            answers[key] = data
            checkAndUpdateButton()
        }
    }

}
