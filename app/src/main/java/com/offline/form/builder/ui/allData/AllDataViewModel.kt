package com.offline.form.builder.ui.allData

import androidx.lifecycle.ViewModel

class AllDataViewModel(
    repo: AllDataRepository = AllDataRepository()
) : ViewModel() {

    val data = repo.getAllData()

}