package xyz.savvamirzoyan.trueithubtalks.repository.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.Credentials
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.CredentialsToken
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserInfoResponse

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun sendCredentials(@Body credentials: Credentials): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("signup")
    fun createUser(@Body credentials: Credentials): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("user-info-token")
    fun getUserInfoByToken(@Body credentials: CredentialsToken): Call<UserInfoResponse>

}