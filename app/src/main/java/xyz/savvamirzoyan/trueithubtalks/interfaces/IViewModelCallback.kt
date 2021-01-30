package xyz.savvamirzoyan.trueithubtalks.interfaces

import xyz.savvamirzoyan.trueithubtalks.repository.API.response.AccountInfoResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatFromSearchResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.ChatsSearchResponse
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.LoginResponse
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Chat
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.request.TextMessageIncome

interface IViewModelCallback {
    interface ILogin {
        fun onCredentialsSuccessResponse(response: LoginResponse)
        fun onCredentialsFailureResponse(t: Throwable?)
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
        fun onSearchUserSuccessResponse(response: ChatsSearchResponse)
        fun onSearchUserFailureResponse(t: Throwable)

        fun onGetPrivateChatSuccessResponse(response: ChatFromSearchResponse)
        fun onGetPrivateChatFailureResponse(t: Throwable)
    }

    interface IChat {
        fun onMessageHistory(data: ArrayList<TextMessageIncome>)
        fun onNewMessage(data: TextMessageIncome)
    }

    interface IChatsFeed {
        fun onChatsFeedDownload(data: ArrayList<Chat>)
        fun onChatsFeedUpdate(data: Chat)

        fun onChatsFeedFailure(t: Throwable)
    }
}