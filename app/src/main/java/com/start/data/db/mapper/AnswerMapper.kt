package com.start.data.db.mapper

import com.start.data.db.table.AnswerTable
import com.start.domain.entity.Answer
import com.start.domain.entity.NumberAnswer
import com.start.domain.entity.SelectionAnswer
import com.start.domain.entity.TextAnswer
import javax.inject.Inject

class AnswerMapper
@Inject
constructor() {

    fun map(value: Answer): AnswerTable = when (value) {
        is NumberAnswer -> {
            AnswerTable(value.id, value.value.toString())
        }
        is SelectionAnswer -> {
            AnswerTable(value.id, value.option.text)
        }
        is TextAnswer -> {
            AnswerTable(value.id, value.value)
        }
    }
}