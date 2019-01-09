package com.bassamworks.wearematchplay.models.api.matches

import com.google.gson.annotations.SerializedName

data class MatchesResponse(
    @SerializedName("current_page")
    val currentPage: Int,
    val `data`: List<Data>,
    @SerializedName("last_page")
    val lastPage: Int,
    val total: Int
)