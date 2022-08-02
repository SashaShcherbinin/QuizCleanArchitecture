package com.start.data.repository.quiz

import com.start.data.rest.mapper.QuizMapper
import com.start.data.rest.service.ApiService
import com.start.domain.entity.Quiz
import retrofit2.Retrofit
import javax.inject.Inject

class QuizNetworkStorage
@Inject
constructor(retrofit: Retrofit, private val quizMapper: QuizMapper) {

    private val _service: ApiService = retrofit.create(ApiService::class.java)

    suspend fun getQuiz(): Quiz = quizMapper.map(_service.getQuiz())
}