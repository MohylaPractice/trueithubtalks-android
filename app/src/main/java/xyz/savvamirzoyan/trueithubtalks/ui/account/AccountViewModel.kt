package xyz.savvamirzoyan.trueithubtalks.ui.account

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.AccountInfoResponse
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController

class AccountViewModel(activity: Activity) : ViewModel(), IViewModelCallback.IAccount {

    val usernameLiveData = MutableLiveData<String>()
    val pictureUrlLiveData = MutableLiveData<String>()

    private val repository = RepositoryController.Account(this, activity)

    init {
        Timber.i("initialized")
        repository.getAccountInfo()
    }

    override fun onAccountInfoSuccessResponse(response: AccountInfoResponse) {
        usernameLiveData.postValue(response.username)
        pictureUrlLiveData.postValue(response.pictureUrl)
    }

    override fun onAccountInfoFailureResponse(t: Throwable) {
        Timber.i("onAccountInfoFailureResponse() called | t: $t")
    }

    override fun onIdInSharedPreferencesFound(id: Int) {}

    override fun onTokenInSharedPreferencesFound(token: String) {}

    override fun onUsernameInSharedPreferencesFound(username: String) {
        usernameLiveData.postValue(username)
    }

    override fun onPictureUrlInSharedPreferencesFound(pictureUrl: String) {
        pictureUrlLiveData.postValue(pictureUrl)
    }
}