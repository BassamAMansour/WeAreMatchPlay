package com.bassamworks.wearematchplay.models.api.matches

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("comment_count")
    val commentCount: Int,
    val course: Course,
    val description: String,
    val id: Int,
    val images: List<Image>,
    @SerializedName("like_count")
    val likeCount: Int,
    val liked: Boolean,
    @SerializedName("played_at")
    val playedAt: String,
    val users: List<User>,
    @SerializedName("verified_at")
    val verifiedAt: String?,
    @SerializedName("winning_score")
    val winningScore: Int,
    @SerializedName("winning_team")
    val winningTeam: Int
)