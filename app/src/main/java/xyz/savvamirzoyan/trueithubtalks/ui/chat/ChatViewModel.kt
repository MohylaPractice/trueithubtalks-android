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
    private val chatId: Int,
    title: String,
    pictureUrl: String,
    activity: Activity
) : ViewModel(), IViewModelCallback.IChat {

    val lastMessage = MutableLiveData<ChatMessage>()
    val messageHistory = MutableLiveData<ArrayList<ChatMessage>>()
    val repository: IRepositoryController.IChat = RepositoryController.Chat(this, activity)

    init {
        Timber.i("initialized")
        repository.establishConnection(chatId)
    }

    override fun onMessageHistory(data: ArrayList<TextMessageIncome>) {
        Timber.i("onMessageHistory() called")
        messageHistory.postValue(
            ArrayList(data.map { ChatMessage(it.message, it.senderId != chatId) })
        )
    }

    override fun onNewMessage(data: TextMessageIncome) {
        Timber.i("onNewMessage() called")
        lastMessage.postValue(ChatMessage(data.message, data.senderId != chatId))
    }

    fun sendMessage(message: String) {
        Timber.i("sendMessage(message: $message) called")
        repository.sendMessage(message)
    }

    //    private val token = repository.getToken()
//    private val webSocketController =
//        WebSocketController.SingleChatController("token", title, lastMessage, messageHistory)
//
//    init {
//        Timber.i("initialized")
//        webSocketController.connectToChat()
//    }
//
//    fun disconnect() {
//        Timber.i("disconnect() called")
//        webSocketController.disconnect()
//    }
//
//    fun sendText(text: String) {
//        Timber.i("sendText() called")
//        webSocketController.sendText(text)
//    }
}