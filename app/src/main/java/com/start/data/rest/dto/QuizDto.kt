package com.start.data.rest.dto


import com.google.gson.annotations.SerializedName

data class QuizDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("questions")
    val questions: List<QuestionDto>,
    @SerializedName("start_question_id")
    val startQuestionId: String,
    @SerializedName("strings")
    val strings: StringsDto
)