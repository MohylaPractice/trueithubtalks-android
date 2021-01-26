package xyz.savvamirzoyan.trueithubtalks.adapter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import xyz.savvamirzoyan.trueithubtalks.R
import xyz.savvamirzoyan.trueithubtalks.interfaces.RecyclerViewItemClickListener
import xyz.savvamirzoyan.trueithubtalks.repository.websockets.jsonserializable.Chat

class ChatsRecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.textViewInChatsUserName)
    val textPreview: TextView = view.findViewById(R.id.textViewInChatsTextPreview)
    val picture: ImageView = view.findViewById(R.id.imageViewInChatsUserPicture)
}

class ChatsRecyclerViewAdapter(
    private val clickListener: RecyclerViewItemClickListener,
    private val chats: ArrayList<Chat>
) : RecyclerView.Adapter<ChatsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsRecyclerViewHolder {
        val viewHolder = ChatsRecyclerViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.user_in_chats_item,
                    parent,
                    false
                )
        )

        viewHolder.itemView.setOnClickListener { clickListener.onChatClick(viewHolder.layoutPosition) }

        return viewHolder

    }

    override fun onBindViewHolder(holder: ChatsRecyclerViewHolder, position: Int) {
        val chat = chats[position]

        holder.name.text = chat.title
        holder.textPreview.text = chat.textPreview

        Picasso.with(holder.picture.context)
            .load(chat.pictureUrl)
            .into(holder.picture)
    }

    override fun getItemCount(): Int = chats.size
    fun getUserByPosition(position: Int): Chat = chats[position]

    fun updateChats(newChats: ArrayList<Chat>) {
        chats.clear()
        chats.addAll(newChats)
        notifyDataSetChanged()
    }
}