package xyz.savvamirzoyan.trueithubtalks.interfaces

import androidx.lifecycle.MutableLiveData

interface ISharedPreferencesController {
    fun getToken(token: MutableLiveData<String>)

    fun getIdValue(): Int
    fun getTokenValue(): String
    fun getUsernameValue(): String
    fun getPictureUrlValue(): String

    fun setId(id: Int)
    fun setToken(token: String)
    fun setUsername(username: String)
    fun setPictureUrl(pictureUrl: String)
}