package com.offline.form.builder.utils

data class Question(
    val id: String,
    val question: String,
    val options: List<OptionType>,
    val validate: Validation
)
