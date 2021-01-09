package xyz.savvamirzoyan.trueithubtalks.repository.websocket

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatMessage
import xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable.MessageFactory
import xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable.Wrapper
import xyz.savvamirzoyan.trueithubtalks.repository.model.jsonconvertable.income.TextMessageIncome
import java.net.SocketException
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

class ChatWebSocketController(
    private val token: String,
    private val username: String,
    private val lastMessage: MutableLiveData<ChatMessage>
) {

    private val socketClient = getUnsafeClient()
    private var serverSocket: WebSocket? = null

    private val serverWebSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
            Timber.i("onOpen() called | response: $response")
            serverSocket = webSocket

            val json = Json.encodeToString(MessageFactory.openChatAction(username, token))

            serverSocket!!.send(json)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Timber.i("onMessage() called | text: $text")

            val type = JSONObject(text).get("type")
            if (type == "new-message") {
                val textMessage = Json.decodeFromString<Wrapper<TextMessageIncome>>(text).data
                lastMessage.postValue(ChatMessage(textMessage.message, false))
            }
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Timber.i("onClosing() called | code: $code | reason: $reason")

            webSocket.close(1000, null)
            webSocket.cancel()
        }

        override fun onFailure(
            webSocket: WebSocket,
            t: Throwable,
            response: okhttp3.Response?
        ) {
            Timber.i("onFailure() called | t: $t ${t.localizedMessage} | response: $response")
            if (t is SocketException) {
                serverSocket = null
            }
            if (t is java.io.EOFException) {
                connect()
            }
        }
    }

    private fun buildInitRequest(username: String): Request.Builder {
        Timber.i("buildInitRequest($username) called")
        return Request.Builder().url("wss://192.168.0.107:8083/")
    }

    fun connect() {
        Timber.i("connect() called")
        socketClient.newWebSocket(buildInitRequest(username).build(), serverWebSocketListener)
    }

    fun disconnect() {
        Timber.i("disconnect() called")

        val json = Json.encodeToString(MessageFactory.disconnectAction(username, token))

        serverSocket?.send(json)
        serverSocket?.close(1000, null)
        serverSocket?.cancel()
    }

    fun sendText(text: String) {
        Timber.i("sendText($text) called")

        val json = Json.encodeToString(MessageFactory.textMessage(username, token, text))
        serverSocket?.send(json)
    }
}