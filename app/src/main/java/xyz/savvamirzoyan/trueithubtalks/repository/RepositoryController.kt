package xyz.savvamirzoyan.trueithubtalks.repository

import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.API.APIController

object RepositoryController {

    fun sendCredentials(name: String, password: String, token: MutableLiveData<String>) {
        Timber.i("sendCredentials($name, $password) called")
        APIController.sendCredentials(name, password, token)
    }

    fun createUser(name: String, password: String, token: MutableLiveData<String>) {
        Timber.i("sendCredentials($name, $password) called")
        APIController.createUser(name, password, token)
    }
}