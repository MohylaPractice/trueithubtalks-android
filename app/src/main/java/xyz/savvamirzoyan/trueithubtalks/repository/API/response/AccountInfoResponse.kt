package xyz.savvamirzoyan.trueithubtalks.repository.API.response

import kotlinx.serialization.Serializable

@Serializable
data class AccountInfoResponse(val id: Int, val username: String, val pictureUrl: String)
