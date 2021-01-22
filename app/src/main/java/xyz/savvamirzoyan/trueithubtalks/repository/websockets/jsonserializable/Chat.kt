package xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable

import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val chatId: Int,
    val title: String,
    val textPreview: String,
    val pictureUrl: String
)//val username: String, val textPreview: String, val pictureUrl: String)
