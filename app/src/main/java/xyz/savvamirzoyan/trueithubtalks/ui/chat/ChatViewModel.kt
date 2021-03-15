package xyz.savvamirzoyan.trueithubtalks.ui.chat

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IRepositoryController
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatMessage
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.request.TextMessageIncome

class ChatViewModel(
    val chatId: Int,
    title: String,
    pictureUrl: String,
    activity: Activity
) : ViewModel(), IViewModelCallback.IChat {

    val lastMessage = MutableLiveData<ChatMessage>()
    val messageHistory = MutableLiveData<ArrayList<ChatMessage>>()
    val repository: IRepositoryController.IChat = RepositoryController.Chat(this, activity)
    private val userId = repository.getId()

    init {
        Timber.i("initialized")
        repository.establishConnection(chatId)
    }

    override fun onMessageHistory(data: ArrayList<TextMessageIncome>) {
        Timber.i("onMessageHistory() called")
        messageHistory.postValue(
            ArrayList(data.map { ChatMessage(it.message, it.senderId == userId) })
        )
    }

    override fun onNewMessage(data: TextMessageIncome) {
        Timber.i("onNewMessage() called")
        lastMessage.postValue(ChatMessage(data.message, data.senderId == userId))
        Timber.i("onMessageHistory() messageHistory: ${messageHistory.value}")
    }

    fun sendMessage(message: String) {
        Timber.i("sendMessage(message: $message) called")
        repository.sendMessage(message)
    }

    fun disconnect() {
        Timber.i("disconnect() called")
        repository.disconnect()
    }
}