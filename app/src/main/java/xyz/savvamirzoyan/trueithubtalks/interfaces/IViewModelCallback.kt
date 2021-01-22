package xyz.savvamirzoyan.trueithubtalks.interfaces

interface IViewModelCallback {
    interface ILogin {
        fun onCredentialsSuccessResponse(token: String)
        fun onCredentialsFailureResponse(t: Throwable)

        fun readTokenFromSharedPreferences(token: String)
    }

    interface ISignUp {
        fun onCredentialsSuccessResponse(token: String)
        fun onCredentialsFailureResponse()
    }

    interface IChat
}