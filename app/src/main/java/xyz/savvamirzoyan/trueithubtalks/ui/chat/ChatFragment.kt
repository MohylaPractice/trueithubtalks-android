package xyz.savvamirzoyan.trueithubtalks.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.savvamirzoyan.trueithubtalks.adapter.RecyclerViewChatAdapter
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var viewModel: ChatViewModel
    private val recyclerViewChatAdapter = RecyclerViewChatAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Binding
        binding = FragmentChatBinding.inflate(inflater, container, false)

        // ViewModel
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        binding.recyclerViewChat.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewChat.adapter = recyclerViewChatAdapter

        return binding.root
    }
}