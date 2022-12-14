package com.offline.form.builder.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "respondent_form_data")
data class AnswerEntity(
    val nameOfRespondent: String,
    val data: String,
    val createdAt: Long,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
)
