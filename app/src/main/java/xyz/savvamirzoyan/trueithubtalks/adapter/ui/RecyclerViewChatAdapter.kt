package xyz.savvamirzoyan.trueithubtalks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.savvamirzoyan.trueithubtalks.R
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatMessage

class ChatMessageMeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.textViewMessageMe)
}

class ChatMessageOtherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.textViewMessageOther)
}

class RecyclerViewChatAdapter(private val messages: ArrayList<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_MESSAGE_ME = 0
    private val TYPE_MESSAGE_OTHER = 1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == TYPE_MESSAGE_ME) {
            ChatMessageMeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.text_message_me_item, parent, false)
            )
        } else {
            ChatMessageOtherViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.text_message_other_item, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        val viewType = getItemViewType(position)

        if (viewType == TYPE_MESSAGE_ME) {
            (holder as ChatMessageMeViewHolder).textView.text = message.text
        } else if (viewType == TYPE_MESSAGE_OTHER) {
            (holder as ChatMessageOtherViewHolder).textView.text = message.text
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isMe) {
            TYPE_MESSAGE_ME
        } else {
            TYPE_MESSAGE_OTHER
        }
    }

    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyDataSetChanged()
    }
}