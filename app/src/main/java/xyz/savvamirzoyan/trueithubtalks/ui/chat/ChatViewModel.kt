package xyz.savvamirzoyan.trueithubtalks.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatMessage
import xyz.savvamirzoyan.trueithubtalks.repository.websocket.WebSocketController

class ChatViewModel(username: String) : ViewModel() {

    val lastMessage = MutableLiveData<ChatMessage>()
    val messageHistory = MutableLiveData<ArrayList<ChatMessage>>()
    private val token = RepositoryController.getTokenValue()
    private val webSocketController =
        WebSocketController.SingleChatController(token, username, lastMessage, messageHistory)

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