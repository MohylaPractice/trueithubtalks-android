package xyz.savvamirzoyan.trueithubtalks.repository.storage

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import timber.log.Timber

private const val PREFERENCES_KEY = "preferences-key"
private const val PREFERENCES_KEY_TOKEN = "preferences-key-token"

class SharedPreferencesController(activity: FragmentActivity) {

    private val preferences = activity.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun getToken(token: MutableLiveData<String>) {
        Timber.i("getToken() called")
        val tokenFromPreferences = preferences.getString(PREFERENCES_KEY_TOKEN, "")

        Timber.i("           tokenFromPreferences: '$tokenFromPreferences'")
        if (tokenFromPreferences != "") token.value = tokenFromPreferences
    }

    fun putToken(tokenValue: String) {
        editor.putString(PREFERENCES_KEY_TOKEN, tokenValue)
        editor.commit()
    }
}