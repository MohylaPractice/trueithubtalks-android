package xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable

import xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable.outcome.TextMessageOutcome

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

}