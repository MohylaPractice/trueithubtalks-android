package xyz.savvamirzoyan.trueithubtalks.factory

import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.ConnectionChatsFeed
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Disconnect
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.OpenChat
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Wrapper
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.response.TextMessageOutcome

object MessageFactory {

    fun textMessage(username: String, token: String, message: String): Wrapper<TextMessageOutcome> {
        return Wrapper("new-message", TextMessageOutcome(username, token, message))
    }

    fun disconnectAction(username: String, token: String): Wrapper<Disconnect> {
        return Wrapper("disconnect", Disconnect(username, token))
    }

    fun openChatAction(username: String, token: String): Wrapper<OpenChat> {
        return Wrapper("open-chat", OpenChat(username, token))
    }

    fun connectToChatsFeed(token: String): Wrapper<ConnectionChatsFeed> {
        return Wrapper("subscribe-chats-feed", ConnectionChatsFeed(token))
    }

    fun disconnectFromChatsFeedAction(token: String): Wrapper<ConnectionChatsFeed> {
        return Wrapper("unsubscribe-chats-feed", ConnectionChatsFeed(token))
    }

}