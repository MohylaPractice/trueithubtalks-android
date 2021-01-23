package xyz.savvamirzoyan.trueithubtalks.interfaces

import androidx.lifecycle.MutableLiveData

interface IRepositoryController {

    interface ILogin {
        fun sendCredentials(username: String, password: String)

        fun retrieveTokenFromSharedPreferences(tokenLiveData: MutableLiveData<String>)

        fun setId(id: Int)
        fun setToken(token: String)
        fun setUsername(username: String)
        fun setPictureUrl(pictureUrl: String)
    }

    interface ISignUp {
        fun sendSignUpCredentials(username: String, password: String)

        fun setId(id: Int)
        fun setToken(token: String)
        fun setUsername(username: String)
        fun setPictureUrl(pictureUrl: String)
    }

    interface IAccount {
        fun getAccountInfo()
    }

    interface ISearch {
        fun searchUser(username: String)
    }

    interface IChat {
        fun establishConnection(chatId: Int)
        fun sendMessage(message: String)
    }
}