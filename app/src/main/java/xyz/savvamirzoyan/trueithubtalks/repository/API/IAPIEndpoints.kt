package xyz.savvamirzoyan.trueithubtalks.repository.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.AccountInfoRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.ChatFromSearchRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.LoginCredentialsRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.UserSearchRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.AccountInfoResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatFromSearchResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatsSearchResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse

interface IAPIEndpoints {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun sendCredentials(@Body body: LoginCredentialsRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("signup")
    fun sendSignUpCredentials(@Body body: LoginCredentialsRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("user-info")
    fun getAccountInfo(@Body body: AccountInfoRequest): Call<AccountInfoResponse>

    @Headers("Content-Type: application/json")
    @POST("user-search")
    fun searchUserByUsername(@Body body: UserSearchRequest): Call<ChatsSearchResponse>

    @Headers("Content-Type: application/json")
    @POST("get-chat-from-search")
    fun getPrivateChat(@Body body: ChatFromSearchRequest): Call<ChatFromSearchResponse>
}