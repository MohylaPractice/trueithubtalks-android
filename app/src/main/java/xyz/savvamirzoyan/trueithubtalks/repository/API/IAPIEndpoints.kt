package xyz.savvamirzoyan.trueithubtalks.repository.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.*
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.*

interface IAPIEndpoints {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun sendCredentials(@Body body: LoginCredentialsRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("signup")
    fun sendSignUpCredentials(@Body body: LoginCredentialsRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("get-account-info")
    fun getAccountInfo(@Body body: AccountInfoRequest): Call<AccountInfoResponse>

    @Headers("Content-Type: application/json")
    @POST("user-search")
    fun searchUserByUsername(@Body body: UserSearchRequest): Call<ChatsSearchResponse>

    @Headers("Content-Type: application/json")
    @POST("get-chat-from-search")
    fun getPrivateChat(@Body body: ChatFromSearchRequest): Call<ChatFromSearchResponse>

    @Headers("Content-Type: application/json")
    @POST("get-private-chat-info")
    fun getPrivateChatInfo(@Body body: ChatInfoRequest): Call<ChatInfoResponse>
}