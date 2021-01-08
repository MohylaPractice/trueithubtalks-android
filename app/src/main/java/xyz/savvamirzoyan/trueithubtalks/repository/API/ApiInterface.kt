package xyz.savvamirzoyan.trueithubtalks.repository.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.CredentialsRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.CredentialsTokenRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.UserSearchRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserFoundResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserInfoResponse

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun sendCredentials(@Body credentialsRequest: CredentialsRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("signup")
    fun createUser(@Body credentialsRequest: CredentialsRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("user-info-token")
    fun getUserInfoByToken(@Body credentialsRequest: CredentialsTokenRequest): Call<UserInfoResponse>

    @Headers("Content-Type: application/json")
    @POST("user-search-username")
    fun searchUserByUsername(@Body credentials: UserSearchRequest): Call<UserFoundResponse>

}