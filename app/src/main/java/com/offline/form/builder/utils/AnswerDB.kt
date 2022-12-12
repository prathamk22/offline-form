package com.offline.form.builder.utils

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Answers::class], version = 1)
abstract class AnswerDB : RoomDatabase(){

    abstract fun answersDao():AnswersDao

}