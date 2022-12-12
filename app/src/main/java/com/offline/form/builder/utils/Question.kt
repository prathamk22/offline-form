package com.offline.form.builder.utils

data class Question(
    val id: String,
    val question: String,
    val optionType: OptionTypeEnum,
    val options: List<OptionType>,
    val validate: Validation<Any>,
    val isOptional: Boolean = false
)

enum class OptionTypeEnum(val id: Int){
    INPUT(0),
    CHECK_BOX(1)
}
