package xyz.savvamirzoyan.trueithubtalks.ui.signup

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController

class SignUpViewModel(activity: Activity) : ViewModel(), IViewModelCallback.ISignUp {

    var tokenLiveData = MutableLiveData<String>()

    var userName = ""
        private set
    var userPassword = ""
        private set
    var userPasswordCopy = ""
        private set

    private val nameFilled: Boolean
        get() = userName.isNotBlank() and userName.isNotEmpty()
    private val passwordMatch: Boolean
        get() = userPassword == userPasswordCopy
    private val passwordFilled: Boolean
        get() = userPassword.isNotBlank() and
                userPassword.isNotEmpty() and
                userPasswordCopy.isNotBlank() and
                userPasswordCopy.isNotEmpty()

    val isSignUpButtonEnabled: Boolean
        get() = (nameFilled and passwordFilled and passwordMatch)

    val repository = RepositoryController.SignUp(this, activity)

    init {
        Timber.i("initialized")
    }

    override fun onCredentialsSuccessResponse(token: String) {
        tokenLiveData.postValue(token)
        repository.setToken(token)
    }

    override fun onCredentialsFailureResponse() {
        repository.setToken("")
    }

    fun updateNameFilled(username: String) {
        Timber.i("updateNameFilled() called | name: $username")
        userName = username
    }

    fun updatePasswordFilled(password: String) {
        Timber.i("updatePasswordFilled() called | password: $password")
        userPassword = password
    }

    fun updatePasswordCopyFilled(passwordCopy: String) {
        Timber.i("updatePasswordFilled() called | passwordCopy: $passwordCopy")
        userPasswordCopy = passwordCopy
    }

    fun sendSignUpCredentials(username: String, password: String) {
        Timber.i("createUser($username, $password) called")
        repository.sendSignUpCredentials(username, password)
    }
}