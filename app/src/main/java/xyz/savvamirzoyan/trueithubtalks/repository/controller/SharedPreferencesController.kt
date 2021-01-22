package xyz.savvamirzoyan.trueithubtalks.repository.controller

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.ISharedPreferencesController

private const val PREFERENCES_KEY = "preferences-key"
private const val PREFERENCES_KEY_TOKEN = "preferences-key-token"

class SharedPreferencesController(activity: Activity) : ISharedPreferencesController {

    private val preferences: SharedPreferences =
        activity.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    override fun getToken(token: MutableLiveData<String>) {
        Timber.i("getToken() called")
        val tokenFromPreferences = preferences.getString(PREFERENCES_KEY_TOKEN, "")

        Timber.i("           tokenFromPreferences: '$tokenFromPreferences'")
        if (tokenFromPreferences != "") token.value = tokenFromPreferences
    }

    override fun getTokenValue(): String {
        Timber.i("getTokenValue() called")
        return preferences.getString(PREFERENCES_KEY_TOKEN, "") ?: ""
    }

    override fun setToken(tokenValue: String) {
        editor.putString(PREFERENCES_KEY_TOKEN, tokenValue)
        editor.apply()
    }
}