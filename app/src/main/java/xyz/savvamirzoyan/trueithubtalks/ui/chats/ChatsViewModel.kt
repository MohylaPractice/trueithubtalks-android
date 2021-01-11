package xyz.savvamirzoyan.trueithubtalks.ui.chats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable.Chat
import xyz.savvamirzoyan.trueithubtalks.repository.websocket.WebSocketController

class ChatsViewModel : ViewModel() {

    val chats = MutableLiveData<ArrayList<Chat>>()
    private val token = RepositoryController.getTokenValue()
    private val webSocketController =
        WebSocketController.ChatFeedController(token, chats)

    init {
        Timber.i("initialized")

        // Initiate connection
        webSocketController.connectToChats()
    }

    fun disconnect() = webSocketController.disconnect()

}