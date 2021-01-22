package xyz.savvamirzoyan.trueithubtalks.interfaces

import androidx.lifecycle.MutableLiveData

interface ISharedPreferencesController {
    fun getToken(token: MutableLiveData<String>)
    fun getTokenValue(): String
    fun setToken(tokenValue: String)
}