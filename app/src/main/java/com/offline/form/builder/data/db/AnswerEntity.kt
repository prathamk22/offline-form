package com.offline.form.builder.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "respondent_form_data")
data class AnswerEntity(
    val nameOfRespondent: String,
    val data: String,
    val createdAt: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = System.currentTimeMillis()
)
