package com.start.domain.usecase.impl

import com.start.domain.entity.Answer
import com.start.domain.entity.Question
import com.start.domain.repository.QuizRepository
import com.start.domain.usecase.QuizUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuizUseCaseImpl
@Inject
constructor(private val quizRepository: QuizRepository) : QuizUseCase {

    override fun getFistQuestion(): Flow<Question> = quizRepository.getQuiz().map { quiz ->
        quiz.question.find { it.id == quiz.startId }!!
    }

    override fun getQuestion(id: String): Flow<Question> = quizRepository.getQuiz().map { quiz ->
        quiz.question.find { it.id == id }!!
    }

    override suspend fun saveAnswer(value: Answer) {
        quizRepository.saveAnswer(value)
    }

    override suspend fun sendAnswers() {
        quizRepository.sendAnswers()
    }
}