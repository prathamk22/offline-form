package com.offline.form.builder.ui.slideshow

import com.offline.form.builder.OfflineFormApp
import com.offline.form.builder.data.db.AnswersDao

class AllDataRepository(
    private val answersDao: AnswersDao = OfflineFormApp.db.answersDao()
) {

    fun getAllData() = answersDao.getAnswer()

}
