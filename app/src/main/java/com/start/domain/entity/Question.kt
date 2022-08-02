package com.start.domain.entity

data class Question(
    val id: String,
    val nextId: String?,
    val type: QuestionType,
    val text: String,
    val options: List<Option>?
)