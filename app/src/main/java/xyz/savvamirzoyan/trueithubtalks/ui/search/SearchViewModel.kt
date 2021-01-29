package xyz.savvamirzoyan.trueithubtalks.ui.search

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.event.Event
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatFromSearchResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatsSearchResponse
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatSearch

class SearchViewModel(activity: Activity) : ViewModel(), IViewModelCallback.ISearch {

    val foundUserList = MutableLiveData<List<ChatSearch>>()
    val chatToOpen = MutableLiveData<Event<ChatFromSearchResponse>>()
    val repository = RepositoryController.Search(this, activity)

    init {
        Timber.i("initialized")
    }

    fun openChat(id: Int) {
        repository.getChat(id)
    }

    fun searchUser(username: String) {
        Timber.i("searchUser(username: $username) called")
        repository.searchUser(username)
    }

    override fun onSearchUserSuccessResponse(response: ChatsSearchResponse) {
        Timber.i("onSearchUserSuccessResponse() called | response: $response")
        foundUserList.postValue(response.chats)
    }

    override fun onSearchUserFailureResponse(t: Throwable) {
        Timber.i("onSearchUserFailureResponse() called | t: $t")
    }

    override fun onGetPrivateChatSuccessResponse(response: ChatFromSearchResponse) {
        Timber.i("onGetPrivateChatSuccessResponse() called")
        chatToOpen.postValue(Event(response))
    }

    override fun onGetPrivateChatFailureResponse(t: Throwable) {
        Timber.i("onGetPrivateChatFailureResponse() called | t: $t")
    }
}