package com.delug3.postsmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.delug3.postsmvvm.databinding.ItemsDetailsCommentsBinding
import com.delug3.postsmvvm.model.Comments


class CommentsAdapter(private val commentsList: MutableList<Comments?>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsDetailsCommentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comments = commentsList[position]
        holder.bind(comments)
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }


    fun setComments(result: List<Comments?>?) {
        result?.let { commentsList.addAll(it) }
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ItemsDetailsCommentsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comments: Comments?) {
            binding.textViewItemsDetailsCommentsName.text = comments?.name
            binding.textViewItemsDetailsCommentsEmail.text = comments?.email
            binding.textViewItemsDetailsCommentsBody.text = comments?.body
        }
    }
}