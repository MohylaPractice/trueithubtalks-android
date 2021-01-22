package xyz.savvamirzoyan.trueithubtalks.factory

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.savvamirzoyan.trueithubtalks.ui.signup.SignUpViewModel

class SignUpViewModelFactory(private val activity: Activity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SignUpViewModel(activity) as T
    }
}