package xyz.savvamirzoyan.trueithubtalks.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatMessage
import xyz.savvamirzoyan.trueithubtalks.repository.websocket.ChatWebSocketController

class ChatViewModel(username: String) : ViewModel() {

    val lastMessage = MutableLiveData<ChatMessage>()
    private val token = RepositoryController.getTokenValue()
    private val webSocketController = ChatWebSocketController(token, username, lastMessage)

    init {
        Timber.i("initialized")

        // Initialize connection with WebSocket
        webSocketController.connect()
    }

    fun disconnect() {
        webSocketController.disconnect()
    }

    fun sendText(text: String) {
        Timber.i("sendText called")
        webSocketController.sendText(text)
    }
}