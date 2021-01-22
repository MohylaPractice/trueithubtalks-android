package xyz.savvamirzoyan.trueithubtalks.repository.controller

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import xyz.savvamirzoyan.trueithubtalks.interfaces.IRepositoryController
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback

class RepositoryController : IRepositoryController {

    class Login(private val viewModelCallback: IViewModelCallback.ILogin, activity: Activity) :
        IRepositoryController.ILogin {
        private val preferences = SharedPreferencesController(activity)

        override fun sendCredentials(username: String, password: String) {
            APIController.sendCredentials(viewModelCallback, username, password)
        }

        override fun retrieveTokenFromSharedPreferences(tokenLiveData: MutableLiveData<String>) {
            preferences.getToken(tokenLiveData)
        }

        override fun setId(id: Int) {
            preferences.setId(id)
        }

        override fun setToken(token: String) {
            preferences.setToken(token)
        }

        override fun setUsername(username: String) {
            preferences.setUsername(username)
        }

        override fun setPictureUrl(pictureUrl: String) {
            preferences.setPictureUrl(pictureUrl)
        }
    }

    class SignUp(private val viewModelCallback: IViewModelCallback.ISignUp, activity: Activity) :
        IRepositoryController.ISignUp {
        private val preferences = SharedPreferencesController(activity)

        override fun sendSignUpCredentials(username: String, password: String) {
            APIController.sendSignUpCredentials(viewModelCallback, username, password)
        }

        override fun setId(id: Int) {
            preferences.setId(id)
        }

        override fun setToken(token: String) {
            preferences.setToken(token)
        }

        override fun setUsername(username: String) {
            preferences.setUsername(username)
        }

        override fun setPictureUrl(pictureUrl: String) {
            preferences.setPictureUrl(pictureUrl)
        }
    }

    class Account(private val viewModelCallback: IViewModelCallback.IAccount, activity: Activity) :
        IRepositoryController.IAccount {
        private val preferences = SharedPreferencesController(activity)

        override fun getAccountInfo() {

            viewModelCallback.onUsernameInSharedPreferencesFound(preferences.getUsernameValue())

            APIController.getAccountInfo(
                viewModelCallback,
                preferences.getTokenValue(),
                preferences.getIdValue()
            )
        }
    }

    class Chat : IRepositoryController.IChat

//    fun sendCredentials(name: String, password: String) {
//        Timber.i("sendCredentials($name, $password) called")
//        APIController.sendCredentials(fragmentApiCallback, name, password)
//    }
//
//    fun createUser(name: String, password: String) {
//        Timber.i("sendCredentials($name, $password) called")
//        APIController.createUser(name, password)
//    }
//
//    fun getTokenValue(): String = preferences.getTokenValue()
//
//
//    fun putToken(tokenValue: String) {
//        Timber.i("putToken() called")
//        preferences.putToken(tokenValue)
//    }
//
//    fun getUserInfo() {
//
//    }
//
//    fun searchUserByUsername(username: String) {
//        Timber.i("searchUserByUsername() called")
////        APIController.searchUserByUsername(preferencesController.getTokenValue(), username)
//    }
}