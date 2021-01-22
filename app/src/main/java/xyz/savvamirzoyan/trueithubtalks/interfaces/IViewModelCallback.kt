package xyz.savvamirzoyan.trueithubtalks.interfaces

import xyz.savvamirzoyan.trueithubtalks.repository.API.response.AccountInfoResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse

interface IViewModelCallback {
    interface ILogin {
        fun onCredentialsSuccessResponse(response: LoginResponse)
        fun onCredentialsFailureResponse(t: Throwable)
    }

    interface ISignUp {
        fun onCredentialsSuccessResponse(response: LoginResponse)
        fun onCredentialsFailureResponse(t: Throwable)
    }

    interface IAccount {
        fun onAccountInfoSuccessResponse(response: AccountInfoResponse)
        fun onAccountInfoFailureResponse(t: Throwable)

        fun onIdInSharedPreferencesFound(id: Int)
        fun onTokenInSharedPreferencesFound(token: String)
        fun onUsernameInSharedPreferencesFound(username: String)
        fun onPictureUrlInSharedPreferencesFound(pictureUrl: String)
    }

    interface IChat
}