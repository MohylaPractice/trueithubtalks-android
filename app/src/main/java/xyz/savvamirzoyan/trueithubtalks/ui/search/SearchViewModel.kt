package xyz.savvamirzoyan.trueithubtalks.ui.search

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.interfaces.IViewModelCallback
import xyz.savvamirzoyan.trueithubtalks.repository.API.response.UserSearchListResponse
import xyz.savvamirzoyan.trueithubtalks.repository.controller.RepositoryController
import xyz.savvamirzoyan.trueithubtalks.repository.model.UserSearch

class SearchViewModel(activity: Activity) : ViewModel(), IViewModelCallback.ISearch {

    val foundUserList = MutableLiveData<List<UserSearch>>()
    val repository = RepositoryController.Search(this, activity)

    init {
        Timber.i("initialized")
    }

    fun searchUser(username: String) {
        Timber.i("searchUser(username: $username) called")
        repository.searchUser(username)
    }

    override fun onSearchUserSuccessResponse(response: UserSearchListResponse) {
        Timber.i("onSearchUserSuccessResponse() called")
        foundUserList.postValue(response.users)
    }

    override fun onSearchUserFailureResponse(t: Throwable) {
        Timber.i("onSearchUserFailureResponse() called")
    }
}