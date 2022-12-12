package com.offline.form.builder.utils

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Answers")
data class Answers(
    @PrimaryKey(autoGenerate = true)
    val id : Long
)
