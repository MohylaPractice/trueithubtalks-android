package xyz.savvamirzoyan.trueithubtalks.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.savvamirzoyan.trueithubtalks.adapter.RecyclerViewChatAdapter
import xyz.savvamirzoyan.trueithubtalks.databinding.FragmentChatBinding
import xyz.savvamirzoyan.trueithubtalks.factory.ChatViewModelFactory
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
            ChatViewModelFactory(
                bundleArgs.chatId,
                bundleArgs.title,
                bundleArgs.pictureUrl,
                context as MainActivity
            )
        ).get(ChatViewModel::class.java)

        binding.recyclerViewChat.layoutManager = LinearLayoutManager(context)
        (binding.recyclerViewChat.layoutManager as LinearLayoutManager).stackFromEnd = true
        binding.recyclerViewChat.adapter = recyclerViewChatAdapter

        setOnChangedLastMessageListener()
        setOnChangedMessageHistoryListener()
        setOnClickButtonSendListener()

        (activity as MainActivity).setCustomActionBarTitle(bundleArgs.title)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        @Suppress("DEPRECATION")
        super.onActivityCreated(savedInstanceState)
        // Hide soft keyboard
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun setOnChangedMessageHistoryListener() {
        viewModel.messageHistory.observe(viewLifecycleOwner) {
            it.forEach { chatMessage -> recyclerViewChatAdapter.addMessage(chatMessage) }
            if (recyclerViewChatAdapter.itemCount >= 1) {
                binding.recyclerViewChat.smoothScrollToPosition(
                    recyclerViewChatAdapter.itemCount - 1
                )
            }
        }
    }

    private fun setOnClickButtonSendListener() {
        binding.buttonSend.setOnClickListener {
            val text = binding.editTextMessage.text.toString()
            if (text.isNotEmpty() && text.isNotBlank()) {
                viewModel.sendMessage(text)
                binding.editTextMessage.text.clear()
            }
        }
    }

    private fun setOnChangedLastMessageListener() {
        viewModel.lastMessage.observe(viewLifecycleOwner) {
            recyclerViewChatAdapter.addMessage(it)
            binding.recyclerViewChat.smoothScrollToPosition(recyclerViewChatAdapter.itemCount - 1)
        }
    }

}