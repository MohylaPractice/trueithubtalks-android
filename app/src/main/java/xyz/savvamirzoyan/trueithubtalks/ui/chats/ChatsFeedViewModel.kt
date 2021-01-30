package xyz.savvamirzoyan.trueithubtalks.ui.chats

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Chat

class ChatsFeedViewModel(activity: Activity) : ViewModel(), IViewModelCallback.IChatsFeed {

    val chatsLiveData = MutableLiveData<ArrayList<Chat>>()

    val repository = RepositoryController.ChatsFeed(this, activity)

    init {
        Timber.i("initialized")
    }

    fun downloadChatsFeed() {
        repository.establishConnection()
    }

    override fun onChatsFeedDownload(data: ArrayList<Chat>) {
        Timber.i("onChatsFeedDownload() called")
        chatsLiveData.postValue(data)
    }

    override fun onChatsFeedUpdate(data: Chat) {
        Timber.i("onChatsFeedUpdate(data: $data) called")
        Timber.i("chatsLiveData: ${chatsLiveData.value}")
        val chatIndexToChange = chatsLiveData.value?.indexOfFirst { it.id == data.id } ?: 0
        val chats = chatsLiveData.value

        if (chats?.size != 0) chats?.set(chatIndexToChange, data)
        else chats.add(data)

        chatsLiveData.postValue(chats)
    }

    override fun onChatsFeedFailure(t: Throwable) {
        Timber.i("onChatsFeedFailure() called | t: $t")
    }

    fun disconnect() = repository.disconnect()
}