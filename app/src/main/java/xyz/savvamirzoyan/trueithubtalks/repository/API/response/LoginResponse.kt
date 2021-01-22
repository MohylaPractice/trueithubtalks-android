package xyz.savvamirzoyan.trueithubtalks.repository.API.response

data class LoginResponse(
    val id: Int,
    val token: String,
    val username: String,
    val pictureUrl: String
)
