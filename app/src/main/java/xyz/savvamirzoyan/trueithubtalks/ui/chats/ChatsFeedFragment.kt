package xyz.savvamirzoyan.trueithubtalks.ui.chats

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import timber.log.Timber
import xyz.savvamirzoyan.trueithubtalks.R
import xyz.savvamirzoyan.trueithubtalks.adapter.ui.ChatsRecyclerViewAdapter
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentChatsBinding
import xyz.savvamirzoyan.trueithubtalks.factory.ChatsFeedViewModelFactory
import xyz.savvamirzoyan.trueithubtalks.interfaces.RecyclerViewItemClickListener
import xyz.savvamirzoyan.trueithubtalks.ui.MainActivity

class ChatsFeedFragment : Fragment(), RecyclerViewItemClickListener {

    private lateinit var binding: FragmentChatsBinding
    private lateinit var viewModel: ChatsFeedViewModel
    private val chatsRecyclerViewAdapter = ChatsRecyclerViewAdapter(this, arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        // Binding
        binding = FragmentChatsBinding.inflate(inflater, container, false)

        // ViewModel
        viewModel = ViewModelProvider(
            this,
            ChatsFeedViewModelFactory(context as MainActivity)
        ).get(ChatsFeedViewModel::class.java)


        binding.recyclerViewChats.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewChats.adapter = chatsRecyclerViewAdapter

        setOnChangedChatsListener()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.downloadChatsFeed()
    }

    private fun setOnChangedChatsListener() {
        viewModel.chatsLiveData.observe(viewLifecycleOwner) {
            chatsRecyclerViewAdapter.updateChats(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Timber.i("onCreateOptionsMenu() called")
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.chats_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.i("onOptionsItemSelected() called")
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun onChatClick(position: Int) {
        val chat = chatsRecyclerViewAdapter.getUserByPosition(position)
        val action = ChatsFeedFragmentDirections.actionChatsFragmentToChatFragment(
            chat.id,
            chat.title,
            chat.pictureUrl
        )
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disconnect()
    }
}