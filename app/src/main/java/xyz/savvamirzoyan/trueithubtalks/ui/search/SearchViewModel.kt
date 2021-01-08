package xyz.savvamirzoyan.trueithubtalks.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.repository.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.model.UserSearch

class SearchViewModel : ViewModel() {

    val foundUserList = MutableLiveData<List<UserSearch>>()

    fun makeSearch(searchQuery: String) {
        RepositoryController.searchUserByUsername(searchQuery, foundUserList)
    }

    init {
        Timber.i("initialized")
    }
}