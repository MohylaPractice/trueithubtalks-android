package xyz.savvamirzoyan.trueithubtalks.interfaces

import xyz.savvamirzoyan.trueithubtalks.repository.API.response.AccountInfoResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserSearchListResponse

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

    interface ISearch {
        fun onSearchUserSuccessResponse(response: UserSearchListResponse)
        fun onSearchUserFailureResponse(t: Throwable)
    }

    interface IChat
}