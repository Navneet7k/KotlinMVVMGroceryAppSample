package com.nkapps.lovelocalsample.data.util

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreference @Inject constructor(
    private val sharedPreferences : SharedPreferences
) {

    fun isFirstAppLaunch(): Boolean {
        return sharedPreferences.getBoolean(Constants.IS_FIRST_APP_LAUNCH, true)
    }

    fun saveFirstAppLaunch(value: Boolean) {
        sharedPreferences.edit().putBoolean(Constants.IS_FIRST_APP_LAUNCH, value).apply()
    }

    fun userIsLoggedIn() : Boolean {
        val token = sharedPreferences.getString(Constants.USER_IS_LOGGED_IN, null)
        return token != null
    }

    fun saveUserAccessToken(token: String) {
        sharedPreferences.edit().putString(Constants.USER_IS_LOGGED_IN, token).apply()
    }

}