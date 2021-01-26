package xyz.savvamirzoyan.trueithubtalks.repository.API.response

import kotlinx.serialization.Serializable

@Serializable
data class ChatFromSearchResponse(val id: Int, val title: String, val pictureUrl: String)
