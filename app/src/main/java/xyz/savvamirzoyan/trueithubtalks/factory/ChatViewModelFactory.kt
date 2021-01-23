package xyz.savvamirzoyan.trueithubtalks.factory

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.savvamirzoyan.trueithubtalks.ui.chat.ChatViewModel

class ChatViewModelFactory(
    private val chatId: Int,
    private val title: String,
    private val pictureUrl: String,
    private val activity: Activity
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ChatViewModel(chatId, title, pictureUrl, activity) as T
    }
}
