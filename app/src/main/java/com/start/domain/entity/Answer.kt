package com.start.domain.entity

sealed class Answer

data class TextAnswer(val id: String, val value: String) : Answer()

data class NumberAnswer(val id: String, val value: Float) : Answer()

data class SelectionAnswer(val id: String, val option: Option) : Answer()