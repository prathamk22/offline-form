package com.offline.form.builder.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnswerEntity::class], version = 1)
abstract class AnswerDB : RoomDatabase() {

    abstract fun answersDao(): AnswersDao

    companion object {

        private var INSTANCE: AnswerDB? = null

        fun getInstance(context: Context): AnswerDB {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AnswerDB::class.java,
                    "offline_form_db",
                ).fallbackToDestructiveMigration().build()
            }

        }
    }

}