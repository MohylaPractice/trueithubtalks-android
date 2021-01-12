package xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable

import kotlinx.serialization.Serializable

@Serializable
data class Chat(val username: String, val textPreview: String, val pictureUrl: String)