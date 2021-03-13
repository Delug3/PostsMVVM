package com.delug3.healios.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delug3.healios.databinding.ItemsPostsBinding
import com.delug3.healios.model.Posts
import com.delug3.healios.postslist.PostsListActivity



class PostsAdapter(
    private val postsListActivity: PostsListActivity,
    private val postsList: MutableList<Posts?>
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posts = postsList[position]
        holder.bind(posts)
        holder.itemView.setOnClickListener { postsListActivity.onPostItemClick(position) }
    }

    override fun getItemCount(): Int {
        return postsList.size
    }


    fun setPosts(result: List<Posts?>?) {

        result?.let { postsList.addAll(it) }
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ItemsPostsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(posts: Posts?) {
            binding.textViewTitle.text = posts?.title
            binding.textViewBody.text = posts?.body
        }
    }


}