package xyz.savvamirzoyan.trueithubtalks.event

class Event<T>(private val content: T) {

    var isHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (!isHandled) {
            isHandled = true
            content
        } else {
            null
        }
    }

    fun peekContent(): T = content
}