package xyz.savvamirzoyan.trueithubtalks.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.RepositoryController

class LoginViewModel : ViewModel() {
    private var nameFilled = MutableLiveData<Boolean>()
    private var passwordFilled = MutableLiveData<Boolean>()
    var token = MutableLiveData<String>()

    val isLoginButtonEnabled: Boolean
        get() = (nameFilled.value == true) and (passwordFilled.value == true)

    var userName = MutableLiveData("name")
    var userPassword = MutableLiveData("password")

    init {
        Timber.i("initialized")

        nameFilled.value =
            userName.value!!.isNotBlank() and userName.value!!.isNotEmpty()
        passwordFilled.value =
            userPassword.value!!.isNotBlank() and userPassword.value!!.isNotEmpty()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared() called. LoginViewModel destroyed")
    }

    fun updateNameFilled(name: String) {
        Timber.i("updateNameFilled() called | name: $name")
        userName.value = name
        nameFilled.value = userName.value!!.isNotBlank() and userName.value!!.isNotEmpty()
    }

    fun updatePasswordFilled(password: String) {
        Timber.i("updatePasswordFilled() called | password: $password")
        userPassword.value = password
        passwordFilled.value =
            userPassword.value!!.isNotBlank() and userPassword.value!!.isNotEmpty()
    }

    fun sendCredentials(name: String, password: String) {
        Timber.i("sendCredentials($name, $password) called")
        RepositoryController.sendCredentials(name, password, token)
    }
}