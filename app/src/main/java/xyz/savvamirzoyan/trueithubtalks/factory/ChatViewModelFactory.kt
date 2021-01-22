package xyz.savvamirzoyan.trueithubtalks.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.savvamirzoyan.trueithubtalks.ui.chat.ChatViewModel

class ChatViewModelFactory(private val username: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ChatViewModel(username) as T
    }
}
