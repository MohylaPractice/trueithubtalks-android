package xyz.savvamirzoyan.trueithubtalks.repository.API

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.Credentials
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.CredentialsToken
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserInfoResponse

object APIController {

    fun sendCredentials(name: String, password: String, token: MutableLiveData<String>) {
        Timber.i("sendCredentials($name, $password) called")

        val call = RetrofitClient.apiInterface.sendCredentials(Credentials(name, password))
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

        val call = RetrofitClient.apiInterface.createUser(Credentials(name, password))
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
        username: MutableLiveData<String>,
        bio: MutableLiveData<String>
    ) {
        Timber.i("getUserInfoByToken() called")

        val call = RetrofitClient.apiInterface.getUserInfoByToken(CredentialsToken(token))
        call.enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(
                call: Call<UserInfoResponse>,
                response: Response<UserInfoResponse>
            ) {
                Timber.i("getUserInfoByToken() -> onResponse() called")

                if (response.body() != null) {
                    name.value = response.body()!!.name
                    username.value = response.body()!!.username
                    bio.value = response.body()!!.bio
                }
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Timber.i("getUserInfoByToken() -> onFailure() called")
            }
        })
    }
}