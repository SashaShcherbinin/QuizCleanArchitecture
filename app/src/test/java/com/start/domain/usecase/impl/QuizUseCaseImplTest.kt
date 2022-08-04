package com.start.domain.usecase.impl

import com.start.domain.entity.Question
import com.start.domain.entity.QuestionType
import com.start.domain.entity.Quiz
import com.start.domain.repository.QuizRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


internal class QuizUseCaseImplTest {

    private val question = Question(
        id = "666",
        nextId = null,
        type = QuestionType.TEXT,
        text = "text",
        options = null
    )
    private val quiz = Quiz(
        startId = "666",
        question = listOf(
            question, question.copy(id = "999")
        )
    )

    @Test
    fun getFistQuestion() = runBlocking {
        /* Given */
        val expectedQuestion = question.copy(id = "5")
        val repository = mock<QuizRepository>() {
            on { getQuiz() } doReturn flowOf(
                quiz.copy(
                    startId = "5",
                    question = listOf(question.copy(id = "10"), expectedQuestion)
                )
            )
        }
        /* When */
        val useCase = QuizUseCaseImpl(repository)
        val actualQuestion = useCase.getFistQuestion().first()
        /* Then */
        Assert.assertEquals(expectedQuestion, actualQuestion)
    }

    @Test
    fun getQuestion() = runBlocking {
        /* Given */
        val expectedQuestion = question.copy(id = "5")
        val repository = mock<QuizRepository>() {
            on { getQuiz() } doReturn flowOf(
                quiz.copy(
                    startId = "5",
                    question = listOf(
                        question.copy(id = "10"),
                        expectedQuestion,
                        question.copy(id = "11"),
                        question.copy(id = "19"),
                    )
                )
            )
        }
        /* When */
        val useCase = QuizUseCaseImpl(repository)
        val actualQuestion = useCase.getQuestion("5").first()
        /* Then */
        Assert.assertEquals(expectedQuestion, actualQuestion)
    }
}