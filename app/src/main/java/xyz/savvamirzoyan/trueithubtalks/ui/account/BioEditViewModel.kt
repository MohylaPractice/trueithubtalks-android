package xyz.savvamirzoyan.trueithubtalks.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class BioEditViewModel : ViewModel() {

    val bio = MutableLiveData<String>()
    val maxBioLength = 10

    init {
        Timber.i("initialized")
    }
}