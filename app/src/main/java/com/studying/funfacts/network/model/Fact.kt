package com.studying.funfacts.network.model

import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("type")
    val type: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("text")
    val text: String
)