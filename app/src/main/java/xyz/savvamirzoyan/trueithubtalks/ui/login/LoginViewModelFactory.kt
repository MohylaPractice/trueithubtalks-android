package xyz.savvamirzoyan.trueithubtalks.ui.login

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class LoginViewModelFactory(private val activity: FragmentActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(activity) as T
    }
}
