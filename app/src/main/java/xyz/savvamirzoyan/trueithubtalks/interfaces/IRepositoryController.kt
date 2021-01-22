package xyz.savvamirzoyan.trueithubtalks.interfaces

import androidx.lifecycle.MutableLiveData

interface IRepositoryController {

    interface ILogin {
        fun sendCredentials(username: String, password: String)

        fun retrieveTokenFromSharedPreferences(tokenLiveData: MutableLiveData<String>)
        fun getToken()
        fun setToken(token: String)
    }

    interface ISignUp {
        fun sendSignUpCredentials(username: String, password: String)

        fun setToken(token: String)
    }

    interface IChat
}