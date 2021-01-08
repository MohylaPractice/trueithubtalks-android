package xyz.savvamirzoyan.trueithubtalks.repository

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.ISharedPreferencesController
import xyz.savvamirzoyan.trueithubtalks.repository.API.APIController
import xyz.savvamirzoyan.trueithubtalks.repository.storage.SharedPreferencesController

object RepositoryController {

    private lateinit var preferencesController: ISharedPreferencesController

    fun setPreferencesController(activity: FragmentActivity) {
        Timber.i("setPreferencesController() called")
        preferencesController = SharedPreferencesController(activity)
    }

    fun sendCredentials(name: String, password: String, token: MutableLiveData<String>) {
        Timber.i("sendCredentials($name, $password) called")
        APIController.sendCredentials(name, password, token)
    }

    fun createUser(name: String, password: String, token: MutableLiveData<String>) {
        Timber.i("sendCredentials($name, $password) called")
        APIController.createUser(name, password, token)
    }

    fun getToken(token: MutableLiveData<String>) {
        Timber.i("getToken() called")
        preferencesController.getToken(token)
    }

    fun putToken(tokenValue: String) {
        Timber.i("putToken() called")
        preferencesController.putToken(tokenValue)
    }

    fun getUserInfoByToken(
        name: MutableLiveData<String>,
        pictureUrl: MutableLiveData<String>
    ) {
        Timber.i("getUserInfoByToken() called")
        APIController.getUserInfoByToken(preferencesController.getTokenValue(), name, pictureUrl)
    }
}