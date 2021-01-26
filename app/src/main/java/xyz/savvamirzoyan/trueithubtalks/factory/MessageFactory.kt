package xyz.savvamirzoyan.trueithubtalks.factory

import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.ConnectionChatsFeed
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Disconnect
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.OpenChat
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Wrapper
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.response.TextMessageOutcome

object MessageFactory {
    fun disconnect(token: String, chatId: Int): Wrapper<Disconnect> {
        return Wrapper("disconnect", Disconnect(token, chatId))
    }

    fun message(token: String, chatId: Int, message: String): Wrapper<TextMessageOutcome> {
        return Wrapper("new-message", TextMessageOutcome(token, chatId, message))
    }

    fun openChat(token: String, chatId: Int): Wrapper<OpenChat> {
        return Wrapper("open-chat", OpenChat(token, chatId))
    }

    fun chatsFeed(token: String): Wrapper<ConnectionChatsFeed> {
        return Wrapper("subscribe-chats-feed", ConnectionChatsFeed(token))
    }

    fun disconnectChatsFeed(token: String): Wrapper<ConnectionChatsFeed> {
        return Wrapper("unsubscribe-chats-feed", ConnectionChatsFeed(token))
    }
}