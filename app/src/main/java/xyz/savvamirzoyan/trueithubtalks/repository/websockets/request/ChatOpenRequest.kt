package xyz.savvamirzoyan.trueithubtalks.repository.websockets.request

import kotlinx.serialization.Serializable

@Serializable
data class ChatOpenRequest(val chatId: Int, val messages: ArrayList<TextMessageIncome>)
