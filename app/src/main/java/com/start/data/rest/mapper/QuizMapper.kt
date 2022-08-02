package com.start.data.rest.mapper

import com.start.data.rest.dto.QuizDto
import com.start.domain.entity.Quiz
import javax.inject.Inject

class QuizMapper
@Inject
constructor(private val questionMapper: QuestionMapper) {

    fun map(dto: QuizDto): Quiz = Quiz(
        startId = dto.startQuestionId,
        question = dto.questions.map { questionMapper.map(it, dto.strings) }
    )
}