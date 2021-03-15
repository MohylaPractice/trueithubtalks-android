package xyz.savvamirzoyan.trueithubtalks.ui.useraccount

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatInfoResponse
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController

class UserAccountViewModel(chatId: Int, activity: Activity) : ViewModel(),
    IViewModelCallback.IUserAccount {

    val usernameLiveData = MutableLiveData<String>()
    val pictureUrlLiveData = MutableLiveData<String>()

    private val repository = RepositoryController.UserAccountInfo(this, activity)

    init {
        Timber.i("initialized")
        repository.getPrivateChatInfo(chatId)
    }

    override fun onGetChatSuccessResponse(response: ChatInfoResponse) {
        Timber.i("onGetChatSuccessResponse(response: $response) called")
        usernameLiveData.postValue(response.username)
        pictureUrlLiveData.postValue(response.pictureUrl)
    }

    override fun onGetChatFailureResponse(t: Throwable) {
        Timber.i("onGetChatFailureResponse(t: $t) called")
    }
}