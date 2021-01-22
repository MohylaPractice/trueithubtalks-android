package xyz.savvamirzoyan.trueithubtalks.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IRepositoryController
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.controller.WebSocketController
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatMessage

class ChatViewModel(username: String) : ViewModel(), IViewModelCallback.IChat {

    val lastMessage = MutableLiveData<ChatMessage>()
    val messageHistory = MutableLiveData<ArrayList<ChatMessage>>()
    val repository: IRepositoryController.IChat = RepositoryController.Chat()

    //    private val token = repository.getToken()
    private val webSocketController =
        WebSocketController.SingleChatController("token", username, lastMessage, messageHistory)

    init {
        Timber.i("initialized")

        // Initialize connection with WebSocket
        webSocketController.connectToChat()
    }

    fun disconnect() {
        webSocketController.disconnect()
    }

    fun sendText(text: String) {
        Timber.i("sendText called")
        webSocketController.sendText(text)
    }
}