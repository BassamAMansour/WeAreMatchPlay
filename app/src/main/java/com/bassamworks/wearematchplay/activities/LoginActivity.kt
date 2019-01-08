package com.bassamworks.wearematchplay.activities

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.bassamworks.wearematchplay.R
import com.bassamworks.wearematchplay.api.LoginService
import com.bassamworks.wearematchplay.api.UserCredentials
import com.bassamworks.wearematchplay.api.UserToken
import com.bassamworks.wearematchplay.constatnts.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val loginService = Retrofit.Builder()
            .baseUrl(Constants.API.API_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)

        val tokenCall = loginService.getUserToken(UserCredentials(username, password))

        tokenCall.enqueue(object : Callback<UserToken> {
            override fun onFailure(call: Call<UserToken>?, t: Throwable?) {
                showWarning(getString(R.string.check_connection))
            }

            override fun onResponse(call: Call<UserToken>?, response: Response<UserToken>?) {

                if (response!!.isSuccessful)
                    setLoginSuccessful(response.body()!!.api_token)
                else
                    showWarning(getString(R.string.check_credentials))
            }

        })
    }

    private fun setLoginSuccessful(tokenId: String) {

        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString(Constants.Preferences.KEY_CURRENT_USER_TOKEN, tokenId)
            .apply()

        finish()
    }

    private fun showWarning(message: String) {
        Snackbar.make(layout_login_activity, message, Snackbar.LENGTH_SHORT).show()
    }
}
