package com.start.data.rest.dto


import com.google.gson.annotations.SerializedName

data class OptionDto(
    @SerializedName("display_text")
    val displayText: String,
    @SerializedName("value")
    val value: String
)