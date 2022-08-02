package com.start.data.db.mapper

import com.start.data.db.table.OptionTable
import com.start.data.db.table.QuestionTable
import com.start.data.db.table.QuizTable
import com.start.domain.entity.Option
import com.start.domain.entity.Question
import com.start.domain.entity.QuestionType
import com.start.domain.entity.Quiz
import javax.inject.Inject

class QuizDbMapper
@Inject
constructor() {

    fun mapToQuiz(value: Quiz): QuizTable = QuizTable(
        id = "-1",
        startId = value.startId
    )

    fun mapToQuestions(value: Quiz): List<QuestionTable> {
        return value.question.map {
            QuestionTable(
                id = it.id,
                quizId = "-1",
                nextId = it.nextId,
                type = it.type.name,
                text = it.text
            )
        }
    }

    fun mapToOptions(value: Quiz): List<OptionTable> {
        return value.question.asSequence().flatMap { question ->
            question.options?.asSequence()?.map {
                OptionTable(
                    questionId = question.id,
                    text = it.text,
                    value = it.value
                )
            } ?: emptySequence()
        }.toList()
    }

    fun map(
        quizTable: QuizTable?,
        questionTable: List<QuestionTable>?,
        optionTable: List<OptionTable>?
    ): Quiz? {
        if (quizTable == null || questionTable == null) return null
        return Quiz(
            startId = quizTable.startId,
            question = questionTable.map { question ->
                Question(
                    id = question.id,
                    nextId = question.nextId,
                    type = QuestionType.valueOf(question.type),
                    text = question.text,
                    options = optionTable?.filter { it.questionId == question.id }?.map {
                        Option(
                            text = it.text,
                            value = it.value
                        )
                    }
                )
            })
    }
}