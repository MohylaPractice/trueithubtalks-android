package xyz.savvamirzoyan.trueithubtalks.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.RepositoryController

class AccountViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val pictureUrl = MutableLiveData<String>()

    init {
        Timber.i("initialized")

        // Send request to get info about user
        RepositoryController.getUserInfoByToken(name, pictureUrl)
    }

}