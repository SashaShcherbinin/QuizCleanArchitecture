package com.start.domain.repository

import com.start.domain.entity.Answer
import com.start.domain.entity.Quiz
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    fun getQuiz(): Flow<Quiz>
    suspend fun saveAnswer(value: Answer)
    suspend fun sendAnswers()
}