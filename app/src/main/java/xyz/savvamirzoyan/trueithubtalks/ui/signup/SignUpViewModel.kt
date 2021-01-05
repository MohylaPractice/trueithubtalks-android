package xyz.savvamirzoyan.trueithubtalks.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.RepositoryController

class SignUpViewModel : ViewModel() {

    var token = MutableLiveData<String>()

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

    init {
        Timber.i("initialized")
    }

    fun updateNameFilled(name: String) {
        Timber.i("updateNameFilled() called | name: $name")
        userName = name
    }

    fun updatePasswordFilled(password: String) {
        Timber.i("updatePasswordFilled() called | password: $password")
        userPassword = password
    }

    fun updatePasswordCopyFilled(passwordCopy: String) {
        Timber.i("updatePasswordFilled() called | passwordCopy: $passwordCopy")
        userPasswordCopy = passwordCopy
    }

    fun createUser(name: String, password: String) {
        Timber.i("createUser($name, $password) called")
        RepositoryController.createUser(name, password, token)
    }

    fun saveToken(tokenValue: String) {
        Timber.i("saveToken($tokenValue) called")
        RepositoryController.putToken(tokenValue)
    }
}