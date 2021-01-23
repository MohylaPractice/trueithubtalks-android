package xyz.savvamirzoyan.trueithubtalks.repository.websockets.response

import kotlinx.serialization.Serializable

@Serializable
data class TextMessageOutcome(val token: String, val chatId: Int, val message: String)
