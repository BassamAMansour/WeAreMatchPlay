package com.bassamworks.wearematchplay.activities

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.FragmentActivity
import com.bassamworks.wearematchplay.R
import com.bassamworks.wearematchplay.constatnts.Constants

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isUserLoggedIn()) startLoginActivity()

        setContentView(R.layout.main_activity)

    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun isUserLoggedIn(): Boolean {

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        val token = preferences.getString(Constants.Preferences.KEY_CURRENT_USER_TOKEN, null)

        return token != null
    }

}
