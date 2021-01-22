package xyz.savvamirzoyan.trueithubtalks.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class AccountViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val pictureUrl = MutableLiveData<String>()

    init {
        Timber.i("initialized")

        // Send request to get info about user
//        RepositoryController.getUserInfoById(name, pictureUrl)
    }

}