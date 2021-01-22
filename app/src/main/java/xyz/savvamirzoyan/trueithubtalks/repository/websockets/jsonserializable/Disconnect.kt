package xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable

import kotlinx.serialization.Serializable

@Serializable
data class Disconnect(val username: String, val token: String)
