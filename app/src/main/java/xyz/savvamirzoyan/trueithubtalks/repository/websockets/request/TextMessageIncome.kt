package xyz.savvamirzoyan.trueithubtalks.repository.websockets.request

import kotlinx.serialization.Serializable

@Serializable
data class TextMessageIncome(val username: String, val sender: String, val message: String)
