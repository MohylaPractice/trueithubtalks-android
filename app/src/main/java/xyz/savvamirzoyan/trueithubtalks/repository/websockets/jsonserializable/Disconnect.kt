package xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable

import kotlinx.serialization.Serializable

@Serializable
data class Disconnect(val token: String, val chatId: Int)
