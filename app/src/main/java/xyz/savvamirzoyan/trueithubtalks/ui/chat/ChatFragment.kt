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
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatMessage
import xyz.savvamirzoyan.trueithubtalks.ui.MainActivity

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
        val bundleArgs = ChatFragmentArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(
            this,
            ChatViewModelFactory(bundleArgs.username)
        ).get(ChatViewModel::class.java)

        binding.recyclerViewChat.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewChat.adapter = recyclerViewChatAdapter

        setOnChangedLastMessageListener()
        setOnClickButtonSendListener()

        (activity as MainActivity).setCustomActionBarTitle(bundleArgs.username)

        return binding.root
    }

    private fun setOnClickButtonSendListener() {
        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotEmpty() && text.isNotBlank()) {
                viewModel.sendText(text)
                recyclerViewChatAdapter.addMessage(ChatMessage(text, true))
                binding.recyclerViewChat.scrollToPosition(recyclerViewChatAdapter.itemCount - 1)
                binding.editTextMessage.text.clear()
            }
        }
    }

    private fun setOnChangedLastMessageListener() {
        viewModel.lastMessage.observe(viewLifecycleOwner) {
            recyclerViewChatAdapter.addMessage(it)
            binding.recyclerViewChat.scrollToPosition(recyclerViewChatAdapter.itemCount - 1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disconnect()
    }
}