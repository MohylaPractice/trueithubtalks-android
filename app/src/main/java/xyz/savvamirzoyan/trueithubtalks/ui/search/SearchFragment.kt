package xyz.savvamirzoyan.trueithubtalks.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.adapter.ChatsFoundRecyclerViewAdapter
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentSearchBinding
import xyz.savvamirzoyan.trueithubtalks.factory.SearchViewModelFactory
import xyz.savvamirzoyan.trueithubtalks.interfaces.RecyclerViewItemClickListener
import xyz.savvamirzoyan.trueithubtalks.ui.MainActivity

class SearchFragment : Fragment(), RecyclerViewItemClickListener {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    private val userFoundRecyclerViewAdapter = ChatsFoundRecyclerViewAdapter(this, arrayListOf())

    private val editTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            viewModel.searchUser(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("onCreateView() called")

        // Binding
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // ViewModel
        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(context as MainActivity)
        ).get(SearchViewModel::class.java)

        setOnChangedTextSearchView()
        setOnChatToOpenListener()
        setOnFoundUserListener()

        binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.searchRecyclerView.adapter = userFoundRecyclerViewAdapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop() called")
        binding.editTextSearch.removeTextChangedListener(editTextWatcher)
    }

    private fun setOnChangedTextSearchView() {
        binding.editTextSearch.addTextChangedListener(editTextWatcher)
    }

    private fun setOnFoundUserListener() {
        viewModel.foundUserList.observe(viewLifecycleOwner) {
            Timber.i("foundUserList: ${viewModel.foundUserList.value}")
            viewModel.foundUserList.value?.let { it1 -> userFoundRecyclerViewAdapter.updateChats(it1) }
        }
    }

    private fun setOnChatToOpenListener() {
        viewModel.chatToOpen.observe(viewLifecycleOwner) { event ->
            Timber.i("chatIdToOpen: ${viewModel.chatToOpen.value?.isHandled}")
            event.getContentIfNotHandled()?.let {
                val action = SearchFragmentDirections.actionSearchFragmentToChatFragment(
                    it.id,
                    it.title,
                    it.pictureUrl
                )
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
    }

    override fun onChatClick(position: Int) {
        Timber.i("onChatClick(position: $position) called")
        val chat = userFoundRecyclerViewAdapter.getChatByPosition(position)
        viewModel.openChat(chat.id)
    }
}