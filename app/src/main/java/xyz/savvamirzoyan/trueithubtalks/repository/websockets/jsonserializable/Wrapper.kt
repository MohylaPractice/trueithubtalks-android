package xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable

import kotlinx.serialization.Serializable

@Serializable
data class Wrapper<T>(val type: String, val data: T)
