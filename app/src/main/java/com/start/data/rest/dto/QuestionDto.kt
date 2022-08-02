package com.start.data.rest.dto


import com.google.gson.annotations.SerializedName

data class QuestionDto(
    @SerializedName("answer_type")
    val answerType: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("next")
    val nextId: String?,
    @SerializedName("options")
    val options: List<OptionDto>,
    @SerializedName("question_text")
    val questionText: String,
    @SerializedName("question_type")
    val questionType: String
)