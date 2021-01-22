package xyz.savvamirzoyan.trueithubtalks.repository.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.LoginCredentialsRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.UserSearchRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserFoundResponse

interface IAPIEndpoints {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun sendCredentials(@Body credentialsRequest: LoginCredentialsRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("signup")
    fun createUser(@Body credentialsRequest: LoginCredentialsRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("user-search-username")
    fun searchUserByUsername(@Body credentials: UserSearchRequest): Call<UserFoundResponse>

}