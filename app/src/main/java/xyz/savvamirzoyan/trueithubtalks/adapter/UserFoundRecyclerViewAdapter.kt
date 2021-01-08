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
import xyz.savvamirzoyan.trueithubtalks.repository.model.UserSearch

class UserFoundViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.textViewUserSearchName)
    val picture: ImageView = view.findViewById(R.id.imageViewUserSearchPicture)
}

class UserFoundRecyclerViewAdapter(
    private val clickListener: RecyclerViewItemClickListener,
    private var users: List<UserSearch>
) :
    RecyclerView.Adapter<UserFoundViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFoundViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_search_item, parent, false)

        val viewHolder = UserFoundViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            clickListener.onItemClick(viewHolder.layoutPosition)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: UserFoundViewHolder, position: Int) {
        val user = users[position]

        holder.name.text = user.name

        Picasso.with(holder.picture.context)
            .load(user.pictureUrl)
            .into(holder.picture)
    }

    override fun getItemCount(): Int = if (users.isNullOrEmpty()) 0 else users.size

    fun updateUsers(newUsers: List<UserSearch>) {
        users = newUsers.also { notifyDataSetChanged() }
    }

    fun getUserByPosition(position: Int) = users[position]
}