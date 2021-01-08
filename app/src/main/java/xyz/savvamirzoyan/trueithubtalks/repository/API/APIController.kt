package xyz.savvamirzoyan.trueithubtalks.repository.API

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.CredentialsRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.CredentialsTokenRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.UserSearchRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserFoundResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserInfoResponse
import xyz.savvamirzoyan.trueithubtalks.repository.model.UserSearch

object APIController {

    fun sendCredentials(name: String, password: String, token: MutableLiveData<String>) {
        Timber.i("sendCredentials($name, $password) called")

        val call = RetrofitClient.apiInterface.sendCredentials(CredentialsRequest(name, password))
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Timber.i("sendCredentials() -> onResponse() called")

                token.value = if (response.body() != null) {
                    response.body()!!.token
                } else {
                    ""
                }

                Timber.i("sendCredentials() -> onResponse() -> token.value: '${token.value}'")
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Timber.i("sendCredentials() -> onFailure")
                Timber.i("call: $call")
                Timber.i("t: $t")
            }
        })
    }

    fun createUser(name: String, password: String, token: MutableLiveData<String>) {
        Timber.i("createUser($name, $password) called")

        val call = RetrofitClient.apiInterface.createUser(CredentialsRequest(name, password))
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Timber.i("createUser() -> onResponse() called")

                token.value = if (response.body() != null) {
                    response.body()!!.token
                } else {
                    ""
                }

                Timber.i("createUser() -> onResponse() -> token.value: '${token.value}'")
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Timber.i("createUser() -> onFailure")
                Timber.i("call: $call")
                Timber.i("t: $t")
            }
        })
    }

    fun getUserInfoByToken(
        token: String,
        name: MutableLiveData<String>,
        pictureUrl: MutableLiveData<String>
    ) {
        Timber.i("getUserInfoByToken() called")

        val call = RetrofitClient.apiInterface.getUserInfoByToken(CredentialsTokenRequest(token))
        call.enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(
                call: Call<UserInfoResponse>,
                response: Response<UserInfoResponse>
            ) {
                Timber.i("getUserInfoByToken() -> onResponse() called")

                if (response.body() != null) {
                    name.value = response.body()!!.username
                    pictureUrl.value = response.body()!!.pictureUrl
                }
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Timber.i("getUserInfoByToken() -> onFailure() called")
            }
        })
    }

    fun searchUserByUsername(
        token: String,
        username: String,
        users: MutableLiveData<List<UserSearch>>
    ) {
        Timber.i("searchUserByUsername() called")

        val call =
            RetrofitClient.apiInterface.searchUserByUsername(UserSearchRequest(token, username))
        call.enqueue(object : Callback<UserFoundResponse> {
            override fun onResponse(
                call: Call<UserFoundResponse>,
                response: Response<UserFoundResponse>
            ) {
                Timber.i("searchUserByUsername() -> onResponse() called")

                Timber.i("response.body() == null -> ${response.body() == null}")

                if (response.body() != null) {
                    Timber.i("users: ${response.body()!!.users}")
                    users.value = response.body()!!.users
                }
            }

            override fun onFailure(call: Call<UserFoundResponse>, t: Throwable) {
                Timber.i("searchUserByUsername() -> onFailure() called. t: $t")
            }
        })
    }
}