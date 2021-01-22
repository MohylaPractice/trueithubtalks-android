package xyz.savvamirzoyan.trueithubtalks.ui.login

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController

class LoginViewModel(activity: Activity) : ViewModel(),
    IViewModelCallback.ILogin {

    val tokenLiveData = MutableLiveData<String>()

    var userName = ""
    var userPassword = ""
    private var nameFilled = false
    private var passwordFilled = false
    val isLoginButtonEnabled: Boolean
        get() = nameFilled and passwordFilled

    private val repository: RepositoryController.Login

    init {
        Timber.i("initialized")

        nameFilled = userName.isNotBlank() and userName.isNotEmpty()
        passwordFilled = userPassword.isNotBlank() and userPassword.isNotEmpty()

        repository = RepositoryController.Login(this, activity)
    }

    fun retrieveTokenFromSharedPreferences() {
        repository.retrieveTokenFromSharedPreferences(tokenLiveData)
    }

    override fun onCredentialsSuccessResponse(response: LoginResponse) {
        tokenLiveData.postValue(response.token)
        repository.setId(response.id)
        repository.setToken(response.token)
        repository.setUsername(response.username)
        repository.setPictureUrl(response.pictureUrl)
    }

    override fun onCredentialsFailureResponse(t: Throwable) {
        tokenLiveData.postValue("")
    }

    fun updateNameFilled(username: String) {
        Timber.i("updateNameFilled() called | name: $username")
        userName = username
        nameFilled = userName.isNotBlank() and userName.isNotEmpty()
    }

    fun updatePasswordFilled(password: String) {
        Timber.i("updatePasswordFilled() called | password: $password")
        userPassword = password
        passwordFilled = userPassword.isNotBlank() and userPassword.isNotEmpty()
    }

    fun sendCredentials(username: String, password: String) {
        Timber.i("sendCredentials($username, $password) called")
        repository.sendCredentials(username, password)
    }
}