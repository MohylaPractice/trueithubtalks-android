package xyz.savvamirzoyan.trueithubtalks.repository.controller

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IRepositoryController
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback

class RepositoryController : IRepositoryController {

    class Login(private val viewModelCallback: IViewModelCallback.ILogin, activity: Activity) :
        IRepositoryController.ILogin {
        private val preferences = SharedPreferencesController(activity)

        override fun sendCredentials(username: String, password: String) {
            Timber.i("sendCredentials() called")
            APIController.sendCredentials(viewModelCallback, username, password)
        }

        override fun retrieveTokenFromSharedPreferences(tokenLiveData: MutableLiveData<String>) {
            Timber.i("retrieveTokenFromSharedPreferences() called")
            preferences.getToken(tokenLiveData)
        }

        override fun setId(id: Int) {
            Timber.i("setId() called")
            preferences.setId(id)
        }

        override fun setToken(token: String) {
            Timber.i("setToken() called")
            preferences.setToken(token)
        }

        override fun setUsername(username: String) {
            Timber.i("setUsername() called")
            preferences.setUsername(username)
        }

        override fun setPictureUrl(pictureUrl: String) {
            Timber.i("setPictureUrl() called")
            preferences.setPictureUrl(pictureUrl)
        }
    }

    class SignUp(private val viewModelCallback: IViewModelCallback.ISignUp, activity: Activity) :
        IRepositoryController.ISignUp {
        private val preferences = SharedPreferencesController(activity)

        override fun sendSignUpCredentials(username: String, password: String) {
            Timber.i("sendSignUpCredentials(username: $username, password: $password) called")
            APIController.sendSignUpCredentials(viewModelCallback, username, password)
        }

        override fun setId(id: Int) {
            Timber.i("setId(id: $id) called")
            preferences.setId(id)
        }

        override fun setToken(token: String) {
            Timber.i("setToken(token: $token) called")
            preferences.setToken(token)
        }

        override fun setUsername(username: String) {
            Timber.i("setUsername(username: $username) called")
            preferences.setUsername(username)
        }

        override fun setPictureUrl(pictureUrl: String) {
            Timber.i("setPictureUrl(pictureUrl: $pictureUrl) called")
            preferences.setPictureUrl(pictureUrl)
        }
    }

    class Account(private val viewModelCallback: IViewModelCallback.IAccount, activity: Activity) :
        IRepositoryController.IAccount {
        private val preferences = SharedPreferencesController(activity)

        override fun getAccountInfo() {
            Timber.i("getAccountInfo() called")
            viewModelCallback.onUsernameInSharedPreferencesFound(preferences.getUsernameValue())

            APIController.getAccountInfo(
                viewModelCallback,
                preferences.getTokenValue(),
                preferences.getIdValue()
            )
        }
    }

    class Search(private val viewModelCallback: IViewModelCallback.ISearch, activity: Activity) :
        IRepositoryController.ISearch {
        private val preferences = SharedPreferencesController(activity)

        override fun searchUser(username: String) {
            Timber.i("searchUser() called")
            APIController.searchUser(viewModelCallback, preferences.getTokenValue(), username)
        }

        override fun getChat(userId: Int) {
            Timber.i("getChat(userId: $userId) called")
            APIController.getChat(
                viewModelCallback,
                preferences.getTokenValue(),
                preferences.getIdValue(),
                userId
            )
        }
    }

    class Chat(private val viewModelCallback: IViewModelCallback.IChat, activity: Activity) :
        IRepositoryController.IChat {
        private val preferences = SharedPreferencesController(activity)
        private lateinit var websocket: WebSocketController.ChatController

        override fun establishConnection(chatId: Int) {
            Timber.i("establishConnection() called")
            websocket = WebSocketController.ChatController(
                viewModelCallback,
                preferences.getTokenValue(),
                chatId
            )
            websocket.establishConnection()
        }

        override fun disconnect() {
            Timber.i("disconnect() called")
            websocket.disconnect()
        }

        override fun sendMessage(message: String) {
            Timber.i("sendMessage(message: $message) called")
            websocket.sendMessage(message)
        }

        override fun getId(): Int {
            return preferences.getIdValue()
        }
    }

    class ChatsFeed(
        private val viewModelCallback: IViewModelCallback.IChatsFeed,
        activity: Activity
    ) : IRepositoryController.IChatsFeed {
        private val preferences = SharedPreferencesController(activity)
        private lateinit var websocket: WebSocketController.ChatsFeedController

        override fun establishConnection() {
            Timber.i("establishConnection() called")
            websocket = WebSocketController.ChatsFeedController(
                viewModelCallback,
                preferences.getTokenValue()
            )
            websocket.establishConnection()
        }

        override fun disconnect() {
            Timber.i("disconnect() called")
            websocket.disconnect()
        }
    }

    class UserAccountInfo(
        private val viewModelCallback: IViewModelCallback.IUserAccount,
        activity: Activity
    ) : IRepositoryController.IUserAccount {
        private val preferences = SharedPreferencesController(activity)

        fun getPrivateChatInfo(chatId: Int) {
            APIController.getPrivateChatInfo(
                viewModelCallback,
                preferences.getTokenValue(),
                preferences.getIdValue(),
                chatId
            )
        }
    }
}