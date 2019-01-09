package com.bassamworks.wearematchplay.repositories

import android.app.Application
import android.util.Log
import com.bassamworks.wearematchplay.api.MatchesService
import com.bassamworks.wearematchplay.constatnts.Constants
import com.bassamworks.wearematchplay.models.api.matches.MatchesResponse
import com.bassamworks.wearematchplay.models.ui.Match
import com.bassamworks.wearematchplay.preferenes.UserPreferenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchesRepository(private val app: Application) {

    companion object {
        private const val TAG = "MatchesRepository"
        private const val INITIAL_MAX_PAGES = 1
        const val INITIAL_PAGE_NUMBER = 1
    }

    var currentPage = INITIAL_PAGE_NUMBER
    var maxPages = INITIAL_MAX_PAGES

    fun getInitialPage(onSuccess: (matches: List<Match>) -> Unit, onError: (error: String) -> Unit) =
        getPage(onSuccess, onError, INITIAL_PAGE_NUMBER)

    fun getNextPage(onSuccess: (matches: List<Match>) -> Unit, onError: (error: String) -> Unit) =
        getPage(onSuccess, onError, currentPage + 1)

    fun getPreviousPage(onSuccess: (matches: List<Match>) -> Unit, onError: (error: String) -> Unit) =
        getPage(onSuccess, onError, currentPage - 1)

    fun getPage(
        onSuccess: (matches: List<Match>) -> Unit, onError: (error: String) -> Unit,
        page: Int
    ) {
        currentPage = when {
            page < 1 -> 1
            page > maxPages -> maxPages
            else -> page
        }

        getMatchesResponseByPage(page, {
            onSuccess(extractMatches(it))
            maxPages = it.total
        }, onError)
    }

    private fun getMatchesResponseByPage(
        page: Int, onSuccess: (matchesResponse: MatchesResponse) -> Unit, onError: (error: String) -> Unit
    ) {

        val token = UserPreferenceManager.getCurrentUserToken(app)

        if (isTokenInvalid(token)) {
            onError("Token Invalid")
            return
        }

        MatchesService(token).getMatches(page)
            .enqueue(
                object : Callback<MatchesResponse> {
                    override fun onFailure(call: Call<MatchesResponse>?, t: Throwable) {
                        Log.d(TAG, "Network Error")
                        onError(t.message ?: "Network Error")
                    }

                    override fun onResponse(call: Call<MatchesResponse>?, response: Response<MatchesResponse>) {
                        Log.d(TAG, "Response: $response")
                        if (response.isSuccessful) {
                            onSuccess(response.body()!!)
                        } else {
                            onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }
                }
            )
    }

    private fun extractMatches(matchesResponse: MatchesResponse): List<Match> {
        val matches = mutableListOf<Match>()

        for (data in matchesResponse.data) {
            val match = Match().apply {

                id = data.id
                isLiked = data.liked
                isVerified = data.verifiedAt != null
                imagesUrls = data.images.map { it.default }.toTypedArray()
                matchDate = data.playedAt.split(" ")[0]
                matchDescription = data.description
                matchLocation = data.course.name
                numComments = data.commentCount
                numLikes = data.likeCount
                playersNames = data.users.map { "${it.firstName[0]}.${it.lastName}" }.toTypedArray()
                playersScores = arrayOf("", "")
                if (data.winningTeam != 0)
                    playersScores[data.winningTeam - 1] = data.winningScore.toString()
            }

            matches.add(match)
        }

        return matches
    }

    private fun isTokenInvalid(token: String): Boolean = token == Constants.Preferences.DEFAULT_KEY_CURRENT_USER_TOKEN
}