package xyz.savvamirzoyan.trueithubtalks.repository.websocket

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatMessage
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
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
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}

class ChatWebSocketController(
    private val username: String,
    private val lastMessage: MutableLiveData<ChatMessage>
) {

    private val socketClient = getUnsafeClient()
    private var serverSocket: WebSocket? = null
    private val serverWebSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
            Timber.i("onOpen() called | response: $response")
            serverSocket = webSocket
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Timber.i("onMessage() called | text: $text")
            lastMessage.postValue(ChatMessage(text, false))
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
            Timber.i("onFailure() called | t: $t | response: $response")
            if (t is java.io.EOFException) connect()
        }
    }

    fun sendText(text: String) {
        Timber.i("sendText($text) called")
        serverSocket?.send(text)
    }

    fun connect() {
        Timber.i("connect() called")
        socketClient.newWebSocket(buildInitRequest(username).build(), serverWebSocketListener)
    }

    private fun buildInitRequest(username: String): Request.Builder {
        Timber.i("buildInitRequest($username) called")
        return Request.Builder().url("wss://192.168.0.107:8083/")
    }
}