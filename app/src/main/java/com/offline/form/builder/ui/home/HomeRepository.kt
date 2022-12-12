package com.offline.form.builder.ui.home

import com.offline.form.builder.OfflineFormApp
import com.offline.form.builder.data.db.AnswerEntity
import com.offline.form.builder.data.db.AnswersDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(
    private val answersDao: AnswersDao = OfflineFormApp.db.answersDao()
) {

    suspend fun insertData(answerEntity: AnswerEntity) = withContext(Dispatchers.IO){ answersDao.insertAnswer(answerEntity) }

}
