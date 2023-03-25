package com.offline.form.builder.ui

abstract class NewBaseTableFragment : BaseTableFragment() {

    override fun onSubmitListCalled() {
        viewModel.submitDataWithSheetAndColumn(getTableKey(), userdataList, getSheetName(), getColumnNames())
    }

    abstract fun getSheetName(): String

    abstract fun getColumnNames(): List<String>

    protected fun getTableKey() = arguments?.getString("formKey", "") ?: ""

}