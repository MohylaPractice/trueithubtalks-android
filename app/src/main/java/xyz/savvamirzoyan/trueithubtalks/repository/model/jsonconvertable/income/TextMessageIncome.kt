package xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable.income

import kotlinx.serialization.Serializable

@Serializable
data class TextMessageIncome(val username: String, val message: String)
