package com.bassamworks.wearematchplay.models.ui

data class Match(
    val playersNames: Array<String> = arrayOf(),
    val playersScores: Array<String> = arrayOf(),
    val imagesUrls: Array<String> = arrayOf(),
    val matchLocation: String = "",
    val matchDate: String = "",
    val matchDescription: String = "",
    val numLikes: Int = 0,
    val isLiked: Boolean = false,
    val numComments: Int = 0,
    val isVerified: Boolean = false
) {
    companion object {
        fun getPlayerPosition(playerNumber: Int) = playerNumber - 1
    }
}