package com.bassamworks.wearematchplay.models.ui

data class Match(
    var id: Int = -1,
    var playersNames: Array<String> = arrayOf(),
    var playersScores: Array<String> = arrayOf(),
    var imagesUrls: Array<String> = arrayOf(),
    var matchLocation: String = "",
    var matchDate: String = "",
    var matchDescription: String = "",
    var numLikes: Int = 0,
    var isLiked: Boolean = false,
    var numComments: Int = 0,
    var isVerified: Boolean = false
) {
    companion object {
        fun getPlayerPosition(playerNumber: Int) = playerNumber - 1
    }
}