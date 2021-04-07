package com.delug3.postsmvvm.postslistdetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.delug3.postsmvvm.adapter.CommentsAdapter
import com.delug3.postsmvvm.databinding.ActivityDetailsPostsBinding
import com.delug3.postsmvvm.factory.PostsViewModelFactory
import com.delug3.postsmvvm.model.Comments


class PostsActivityDetails : AppCompatActivity() {
    var userId: Int = 0
    lateinit var binding: ActivityDetailsPostsBinding
    private var commentsAdapter: CommentsAdapter? = null
    var commentsList: MutableList<Comments?> = ArrayList()
    private val postsDetailsViewModel: PostsDetailsViewModel by viewModels { PostsViewModelFactory(userId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title: String?
        val body: String?

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
            } else {
                userId = extras.getInt("POST_USER_ID")
                title = extras.getString("POST_TITLE")
                body = extras.getString("POST_BODY")

                binding.textViewDetailsTitle.text = title
                binding.textViewDetailsBody.text = body

            }
        } else {
            savedInstanceState.getSerializable("POST_USER_ID") as Int
            //savedInstanceState.getSerializable("POST_BODY") as String?

        }

        setUpRecyclerView()
        setUserName()
        sendDataToRecyclerView()
    }


    private fun setUpRecyclerView() {
        commentsAdapter = CommentsAdapter(this, commentsList)
        binding.recyclerViewDetailsComments.adapter = commentsAdapter
        binding.recyclerViewDetailsComments.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDetailsComments.layoutManager = layoutManager

        binding.recyclerViewDetailsComments.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewDetailsComments.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }


    private fun setUserName() {
        //do something with the value which is 'it'
        postsDetailsViewModel.getUserName()?.observe(this, Observer {
            binding.textViewDetailsUserName.text = it
        })

    }

    private fun sendDataToRecyclerView() {
        postsDetailsViewModel.getComments()?.observe(this, Observer { comments ->
            comments?.let { commentsAdapter?.setComments(it) }
        })

    }

}