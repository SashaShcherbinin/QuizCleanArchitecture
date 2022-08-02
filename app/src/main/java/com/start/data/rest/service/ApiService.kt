package com.start.data.rest.service

import com.start.data.rest.dto.QuizDto
import retrofit2.http.GET

interface ApiService {

    @GET("/v3/d628facc-ec18-431d-a8fc-9c096e00709a")
    suspend fun getQuiz(): QuizDto

}