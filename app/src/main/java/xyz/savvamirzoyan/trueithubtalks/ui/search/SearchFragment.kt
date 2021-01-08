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
import xyz.savvamirzoyan.trueithubtalks.adapter.UserFoundRecyclerViewAdapter
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentSearchBinding
import xyz.savvamirzoyan.trueithubtalks.interfaces.RecyclerViewItemClickListener

class SearchFragment : Fragment(), RecyclerViewItemClickListener {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    private val userFoundRecyclerViewAdapter = UserFoundRecyclerViewAdapter(this, arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Binding
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        setOnChangedTextSearchView()
        setOnFoundUserListener()

        binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.searchRecyclerView.adapter = userFoundRecyclerViewAdapter

        return binding.root
    }

    private fun setOnChangedTextSearchView() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.makeSearch(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setOnFoundUserListener() {
        viewModel.foundUserList.observe(viewLifecycleOwner) {
            Timber.i("found users: ${viewModel.foundUserList.value}")
            viewModel.foundUserList.value?.let { it1 -> userFoundRecyclerViewAdapter.updateUsers(it1) }
        }
    }

    override fun onItemClick(position: Int) {
        val user = userFoundRecyclerViewAdapter.getUserByPosition(position)

        val action =
            SearchFragmentDirections.actionSearchFragmentToChatFragment(user.name)
        NavHostFragment.findNavController(this).navigate(action)
    }
}