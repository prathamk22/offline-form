package com.offline.form.builder.ui.slideshow

import androidx.lifecycle.ViewModel

class AllDataViewModel(
    repo: AllDataRepository = AllDataRepository()
) : ViewModel() {

    val data = repo.getAllData()

}