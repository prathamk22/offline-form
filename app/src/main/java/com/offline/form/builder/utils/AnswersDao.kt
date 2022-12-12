package com.offline.form.builder.utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswersDao {
    @Insert
    suspend fun insertAnswer(answers: Answers)

    @Update
    suspend fun updateAnswer(answers: Answers)

    @Delete
    suspend fun deleteAnswer(answers: Answers)

    @Query("Select * from Answers")
    fun getAnswer():LiveData<List<Answers>>

}