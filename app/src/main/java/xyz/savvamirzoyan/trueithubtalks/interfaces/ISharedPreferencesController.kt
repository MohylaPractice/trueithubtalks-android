package xyz.savvamirzoyan.trueithubtalks.interfaces

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData

interface ISharedPreferencesController {
    val preferences: SharedPreferences
    val editor: SharedPreferences.Editor

    fun getToken(token: MutableLiveData<String>)
    fun getTokenValue(): String
    fun putToken(tokenValue: String)
}