package xyz.savvamirzoyan.trueithubtalks.repository.controller

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.ISharedPreferencesController

private const val PREFERENCES_KEY = "preferences-key"
private const val PREFERENCES_KEY_ID = "preferences-key-id"
private const val PREFERENCES_KEY_TOKEN = "preferences-key-token"
private const val PREFERENCES_KEY_USERNAME = "preferences-key-username"
private const val PREFERENCES_KEY_PICTURE_URL = "preferences-key-picture-url"

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

    override fun setId(id: Int) {
        editor.putInt(PREFERENCES_KEY_ID, id)
        editor.apply()
    }

    override fun setToken(token: String) {
        editor.putString(PREFERENCES_KEY_TOKEN, token)
        editor.apply()
    }

    override fun setUsername(username: String) {
        editor.putString(PREFERENCES_KEY_USERNAME, username)
        editor.apply()
    }

    override fun setPictureUrl(pictureUrl: String) {
        editor.putString(PREFERENCES_KEY_PICTURE_URL, pictureUrl)
        editor.apply()
    }

    override fun getIdValue(): Int {
        Timber.i("getIdValue() called")
        return preferences.getInt(PREFERENCES_KEY_ID, 0)
    }

    override fun getTokenValue(): String {
        Timber.i("getTokenValue() called")
        return preferences.getString(PREFERENCES_KEY_TOKEN, "") ?: ""
    }

    override fun getUsernameValue(): String {
        Timber.i("getUsernameValue() called")
        return preferences.getString(PREFERENCES_KEY_USERNAME, "") ?: ""
    }

    override fun getPictureUrlValue(): String {
        Timber.i("getPictureUrlValue() called")
        return preferences.getString(PREFERENCES_KEY_PICTURE_URL, "") ?: ""
    }
}