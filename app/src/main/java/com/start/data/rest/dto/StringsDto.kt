package com.start.data.rest.dto


import com.google.gson.annotations.SerializedName

data class StringsDto(
    @SerializedName("en")
    val en: Map<String, String>
)