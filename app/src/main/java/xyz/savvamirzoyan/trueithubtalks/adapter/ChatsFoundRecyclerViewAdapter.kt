package xyz.savvamirzoyan.trueithubtalks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import xyz.savvamirzoyan.trueithubtalks.R
import xyz.savvamirzoyan.trueithubtalks.interfaces.RecyclerViewItemClickListener
import xyz.savvamirzoyan.trueithubtalks.repository.model.ChatSearch

class ChatsFoundViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.textViewChatsSearchTitle)
    val picture: ImageView = view.findViewById(R.id.imageViewChatsSearchPicture)
}

class ChatsFoundRecyclerViewAdapter(
    private val clickListener: RecyclerViewItemClickListener,
    private var chats: List<ChatSearch>
) :
    RecyclerView.Adapter<ChatsFoundViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsFoundViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chats_search_item, parent, false)
        val viewHolder = ChatsFoundViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            clickListener.onChatClick(viewHolder.layoutPosition)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ChatsFoundViewHolder, position: Int) {
        val chat = chats[position]

        holder.title.text = chat.title

        Picasso.with(holder.picture.context)
            .load(chat.pictureUrl)
            .into(holder.picture)
    }

    override fun getItemCount(): Int = if (chats.isNullOrEmpty()) 0 else chats.size

    fun updateChats(newChats: List<ChatSearch>) {
        chats = newChats.also { notifyDataSetChanged() }
    }

    fun getChatByPosition(position: Int) = chats[position]
}