package com.start.domain.usecase

import com.start.domain.entity.Answer
import com.start.domain.entity.Question
import kotlinx.coroutines.flow.Flow

interface QuizUseCase {

    fun getFistQuestion(): Flow<Question>
    fun getQuestion(id: String): Flow<Question>
    suspend fun saveAnswer(value: Answer)
    suspend fun sendAnswers()
}