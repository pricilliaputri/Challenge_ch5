package com.punyacile.challenge_ch5.model


import com.google.gson.annotations.SerializedName

data class moviePopular<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)