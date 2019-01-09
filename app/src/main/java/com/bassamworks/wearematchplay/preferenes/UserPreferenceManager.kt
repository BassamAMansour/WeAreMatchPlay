package com.bassamworks.wearematchplay.preferenes

import android.content.Context
import android.preference.PreferenceManager
import com.bassamworks.wearematchplay.constatnts.Constants

object UserPreferenceManager {

    fun setCurrentUserToken(context: Context, token: String) {

        var tokenModified = token

        if (token.isEmpty() || token == "null") tokenModified = Constants.Preferences.DEFAULT_KEY_CURRENT_USER_TOKEN

        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(Constants.Preferences.KEY_CURRENT_USER_TOKEN, tokenModified)
            .apply()
    }

    fun getCurrentUserToken(context: Context): String {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(
                Constants.Preferences.KEY_CURRENT_USER_TOKEN,
                Constants.Preferences.DEFAULT_KEY_CURRENT_USER_TOKEN
            ) ?: Constants.Preferences.DEFAULT_KEY_CURRENT_USER_TOKEN
    }
}