package xyz.savvamirzoyan.trueithubtalks.repository.controller

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.API.RetrofitClient
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.AccountInfoRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.ChatFromSearchRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.LoginCredentialsRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.request.UserSearchRequest
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.AccountInfoResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatFromSearchResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatsSearchResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse

object APIController {

    fun sendCredentials(callback: IViewModelCallback.ILogin, name: String, password: String) {
        Timber.i("sendCredentials($name, $password) called")

        val call =
            RetrofitClient.apiInterface.sendCredentials(LoginCredentialsRequest(name, password))
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Timber.i("sendCredentials() -> onResponse() called")
                Timber.i("Status code: ${response.code()} | token: ${response.body()?.token}")
                if (response.code() == 200) callback.onCredentialsSuccessResponse(response.body()!!)
                else callback.onCredentialsFailureResponse(null)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Timber.i("sendCredentials() -> onFailure")
                Timber.i("call: $call")
                Timber.i("t: $t")
                callback.onCredentialsFailureResponse(t)
            }
        })
    }

    fun sendSignUpCredentials(
        callback: IViewModelCallback.ISignUp,
        username: String,
        password: String
    ) {
        Timber.i("createUser($username, $password) called")

        val call =
            RetrofitClient.apiInterface.sendSignUpCredentials(
                LoginCredentialsRequest(
                    username,
                    password
                )
            )
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Timber.i("createUser() -> onResponse() called")
                Timber.i("Status code: ${response.code()}")
                callback.onCredentialsSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Timber.i("createUser() -> onFailure")
                Timber.i("call: $call")
                Timber.i("t: $t")
                callback.onCredentialsFailureResponse(t)
            }
        })
    }

    fun getAccountInfo(
        callback: IViewModelCallback.IAccount,
        token: String,
        userId: Int
    ) {
        Timber.i("getAccountInfo() called")

        val call = RetrofitClient.apiInterface.getAccountInfo(AccountInfoRequest(token, userId))
        call.enqueue(object : Callback<AccountInfoResponse> {
            override fun onResponse(
                call: Call<AccountInfoResponse>,
                response: Response<AccountInfoResponse>
            ) {
                Timber.i("onResponse() called")
                callback.onAccountInfoSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<AccountInfoResponse>, t: Throwable) {
                Timber.i("onFailure() called")
                callback.onAccountInfoFailureResponse(t)
            }
        })
    }

    fun searchUser(
        callback: IViewModelCallback.ISearch,
        token: String,
        username: String
    ) {
        Timber.i("searchUser() called")

        val call =
            RetrofitClient.apiInterface.searchUserByUsername(UserSearchRequest(token, username))
        call.enqueue(object : Callback<ChatsSearchResponse> {
            override fun onResponse(
                call: Call<ChatsSearchResponse>,
                response: Response<ChatsSearchResponse>
            ) {
                Timber.i("onResponse() called | response: ${response.body()}")
                callback.onSearchUserSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<ChatsSearchResponse>, t: Throwable) {
                Timber.i("onFailure() called")
                callback.onSearchUserFailureResponse(t)
            }
        })
    }

    fun getChat(
        callback: IViewModelCallback.ISearch,
        token: String,
        userId1: Int,
        id: Int
    ) {
        Timber.i("getPrivateChat(token: $token, userId1: $userId1, userId2: $id) called")

        val call =
            RetrofitClient.apiInterface.getPrivateChat(ChatFromSearchRequest(token, userId1, id))
        call.enqueue(object : Callback<ChatFromSearchResponse> {
            override fun onResponse(
                call: Call<ChatFromSearchResponse>,
                response: Response<ChatFromSearchResponse>
            ) {
                Timber.i("onResponse() called")
                callback.onGetPrivateChatSuccessResponse(response.body()!!)
            }

            override fun onFailure(call: Call<ChatFromSearchResponse>, t: Throwable) {
                Timber.i("onFailure() called")
                callback.onGetPrivateChatFailureResponse(t)
            }
        })
    }
}