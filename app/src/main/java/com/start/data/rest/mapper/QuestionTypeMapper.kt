package com.start.data.rest.mapper

import com.start.domain.entity.QuestionType
import javax.inject.Inject

class QuestionTypeMapper
@Inject
constructor() {

    fun map(type: String): QuestionType = when (type) {
        "FREE_TEXT" -> QuestionType.TEXT
        "SELECT_ONE" -> QuestionType.CHECKBOX
        "TYPE_VALUE" -> QuestionType.NUMBER
        else -> error("not possible case")
    }
}