package xyz.savvamirzoyan.trueithubtalks.repository.controller

import android.annotation.SuppressLint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*
import org.json.JSONObject
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.factory.MessageFactory
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Chat
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Wrapper
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.request.ChatOpenRequest
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.request.TextMessageIncome
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

private fun getUnsafeClient(): OkHttpClient {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { _, _ -> true }
        builder.pingInterval(30, TimeUnit.SECONDS)
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

class WebSocketController {

    class ChatsFeedController(
        val callback: IViewModelCallback.IChatsFeed,
        val token: String
    ) {
        private val url = "wss://192.168.0.105:8083/chats-feed"

        private val socketClient = getUnsafeClient()
        private var serverSocket: WebSocket? = null

        private val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)

                val json = Json.encodeToString(MessageFactory.chatsFeed(token))

                serverSocket = webSocket
                serverSocket!!.send(json)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)

                Timber.i("onMessage(text: $text) called")

                when (JSONObject(text).get("type")) {
                    "chat-feed-download" -> {
                        val data = Json.decodeFromString<Wrapper<ArrayList<Chat>>>(text).data
                        callback.onChatsFeedDownload(data)
                    }

                    "chat-feed-update" -> {
                        val data = Json.decodeFromString<Wrapper<Chat>>(text).data
                        callback.onChatsFeedUpdate(data)
                    }
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                callback.onChatsFeedFailure(t)
            }
        }

        fun establishConnection() {
            Timber.i("establishConnection() called")
            socketClient.newWebSocket(requestBuilder().build(), listener)
        }

        fun disconnect() {
            Timber.i("disconnect() called")
            serverSocket?.send(Json.encodeToString(MessageFactory.disconnectChatsFeed(token)))
        }

        private fun requestBuilder(): Request.Builder = Request.Builder().url(url)
    }

    class ChatController(
        private val callback: IViewModelCallback.IChat,
        private val token: String,
        private var chatId: Int
    ) {
        private val url = "wss://192.168.0.105:8083/chat"

        private val socketClient = getUnsafeClient()
        private var serverSocket: WebSocket? = null

        private val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Timber.i("onOpen() called")

                val json = Json.encodeToString(MessageFactory.openChat(token, chatId))

                serverSocket = webSocket
                serverSocket!!.send(json)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Timber.i("onMessage() called | text: $text")

                when (JSONObject(text).get("type")) {
                    "new-message" -> {
                        val data = Json.decodeFromString<Wrapper<TextMessageIncome>>(text).data
                        callback.onNewMessage(data)
                    }

                    "message-history" -> {
                        val data = Json.decodeFromString<Wrapper<ChatOpenRequest>>(text).data
                        chatId = data.chatId
                        callback.onMessageHistory(data.messages)
                    }
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Timber.i("onFailure() called")
                Timber.i("t: $t | response: $response")
            }
        }

        fun establishConnection() {
            Timber.i("establishConnection() called")
            socketClient.newWebSocket(requestBuilder().build(), listener)
        }

        fun disconnect() {
            Timber.i("disconnect() called")
            serverSocket?.send(Json.encodeToString(MessageFactory.disconnect(token, chatId)))
        }

        fun sendMessage(message: String) {
            Timber.i("sendMessage(message: $message) called")
            val json = Json.encodeToString(MessageFactory.message(token, chatId, message))
            serverSocket?.send(json)
        }

        private fun requestBuilder(): Request.Builder = Request.Builder().url(url)
    }
}