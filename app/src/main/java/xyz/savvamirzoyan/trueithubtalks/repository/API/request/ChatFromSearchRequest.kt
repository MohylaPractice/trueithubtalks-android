package xyz.savvamirzoyan.trueithubtalks.repository.API.request

import kotlinx.serialization.Serializable

@Serializable
data class ChatFromSearchRequest(val token: String, val userId: Int, val id: Int)
