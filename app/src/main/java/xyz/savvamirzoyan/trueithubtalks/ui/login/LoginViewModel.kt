package xyz.savvamirzoyan.trueithubtalks.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.RepositoryController

class LoginViewModel : ViewModel() {

    var token = MutableLiveData<String>()

    private var nameFilled = false
    private var passwordFilled = false
    var userName = ""
    var userPassword = ""

    val isLoginButtonEnabled: Boolean
        get() = (nameFilled and passwordFilled)

    init {
        Timber.i("initialized")

        nameFilled = userName.isNotBlank() and userName.isNotEmpty()
        passwordFilled = userPassword.isNotBlank() and userPassword.isNotEmpty()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared() called. LoginViewModel destroyed")
    }

    fun updateNameFilled(name: String) {
        Timber.i("updateNameFilled() called | name: $name")
        userName = name
        nameFilled = userName.isNotBlank() and userName.isNotEmpty()
    }

    fun updatePasswordFilled(password: String) {
        Timber.i("updatePasswordFilled() called | password: $password")
        userPassword = password
        passwordFilled = userPassword.isNotBlank() and userPassword.isNotEmpty()
    }

    fun sendCredentials(name: String, password: String) {
        Timber.i("sendCredentials($name, $password) called")
        RepositoryController.sendCredentials(name, password, token)
    }
}