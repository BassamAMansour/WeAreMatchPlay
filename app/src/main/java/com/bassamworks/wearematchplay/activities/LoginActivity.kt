package com.bassamworks.wearematchplay.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bassamworks.wearematchplay.R
import com.bassamworks.wearematchplay.api.LoginService
import com.bassamworks.wearematchplay.constatnts.Constants
import com.bassamworks.wearematchplay.models.api.login.UserCredentials
import com.bassamworks.wearematchplay.models.api.login.UserTokenResponse
import com.bassamworks.wearematchplay.preferenes.UserPreferenceManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isUserLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener { startLoggingInProcedure() }
    }

    private fun startLoggingInProcedure() {

        val username = et_username.text.toString().trim()
        val password = et_password.text.toString().trim()

        if (username.isEmpty()) showWarning(getString(R.string.please_enter_username))
        if (password.isEmpty()) showWarning(getString(R.string.please_enter_password))

        startLoggingInProcedure(username, password)
    }

    private fun startLoggingInProcedure(username: String, password: String) {

        val loginService = LoginService()

        val tokenCall = loginService.getUserToken(UserCredentials(username, password))

        tokenCall.enqueue(object : Callback<UserTokenResponse> {
            override fun onFailure(call: Call<UserTokenResponse>?, t: Throwable?) {
                showWarning(getString(R.string.check_connection))
            }

            override fun onResponse(call: Call<UserTokenResponse>?, response: Response<UserTokenResponse>) {

                if (response.isSuccessful)
                    setLoginSuccessful(response.body()!!.api_token)
                else
                    showWarning(response.errorBody()?.string() ?: "Unknown error")
            }

        })
    }

    private fun isUserLoggedIn(): Boolean {

        val token = UserPreferenceManager.getCurrentUserToken(this)

        return token != Constants.Preferences.DEFAULT_KEY_CURRENT_USER_TOKEN
    }

    private fun setLoginSuccessful(token: String) {
        UserPreferenceManager.setCurrentUserToken(this, token)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showWarning(message: String) {
        Snackbar.make(layout_login_activity, message, Snackbar.LENGTH_SHORT).show()
    }
}
