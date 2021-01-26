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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

    private fun setOnChangedTextSearchView() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchUser(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setOnFoundUserListener() {
        viewModel.foundUserList.observe(viewLifecycleOwner) {
            Timber.i("foundUserList: ${viewModel.foundUserList.value}")
            viewModel.foundUserList.value?.let { it1 -> userFoundRecyclerViewAdapter.updateChats(it1) }
        }
    }

    private fun setOnChatToOpenListener() {
        viewModel.chatToOpen.observe(viewLifecycleOwner) {
            Timber.i("chatIdToOpen: ${viewModel.chatToOpen.value}")
            val action = SearchFragmentDirections.actionSearchFragmentToChatFragment(
                viewModel.chatToOpen.value!!.id,
                viewModel.chatToOpen.value!!.title,
                viewModel.chatToOpen.value!!.pictureUrl
            )
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    override fun onChatClick(position: Int) {
        val chat = userFoundRecyclerViewAdapter.getChatByPosition(position)
        viewModel.openChat(chat.id)
    }
}