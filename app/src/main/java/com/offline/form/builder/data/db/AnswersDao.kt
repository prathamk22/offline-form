package com.offline.form.builder.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswersDao {

    @Insert
    suspend fun insertAnswer(answerEntity: AnswerEntity)

    @Update
    suspend fun updateAnswer(answerEntity: AnswerEntity)

    @Delete
    suspend fun deleteAnswer(answerEntity: AnswerEntity)

    @Query("Select * from respondent_form_data order by createdAt desc")
    fun getAnswer(): LiveData<List<AnswerEntity>>

}