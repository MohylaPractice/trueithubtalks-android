package xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable.outcome

import kotlinx.serialization.Serializable

@Serializable
data class TextMessageOutcome(val username: String, val token: String, val message: String)
