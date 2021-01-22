package xyz.savvamirzoyan.trueithubtalks.repository.websockets.response

import kotlinx.serialization.Serializable

@Serializable
data class TextMessageOutcome(val username: String, val token: String, val message: String)
