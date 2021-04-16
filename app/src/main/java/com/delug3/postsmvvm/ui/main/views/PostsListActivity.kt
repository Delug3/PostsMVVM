package com.delug3.postsmvvm.ui.main.views

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.delug3.postsmvvm.ui.main.adapter.PostsAdapter
import com.delug3.postsmvvm.workers.PostsApplication
import com.delug3.postsmvvm.databinding.ActivityListPostsBinding
import com.delug3.postsmvvm.data.model.Posts
import com.delug3.postsmvvm.listeners.PostItemClickListener
import com.delug3.postsmvvm.ui.main.viewmodels.PostsListViewModel


class PostsListActivity : AppCompatActivity(), PostItemClickListener {
    private val TAG = "POSTS"

    //private lateinit var postsRoomViewModel: PostsRoomViewModel
    private val postsListViewModel: PostsListViewModel by viewModels{
        PostsListViewModel.PostsViewModelFactory(
            (application as PostsApplication).repository
        )
    }

    lateinit var binding: ActivityListPostsBinding
    private var postsAdapter: PostsAdapter? = null

    var postsList: MutableList<Posts?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        readData()
    }


    private fun setUpRecyclerView() {
        postsAdapter = PostsAdapter(this, postsList)
        binding.recyclerViewPosts.adapter = postsAdapter
        binding.recyclerViewPosts.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPosts.layoutManager = layoutManager

        binding.recyclerViewPosts.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewPosts.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }

    private fun readData() {
        val connection = isInternetAvailable(this)
        if (connection) {
            sendOnlineDataToRecyclerView()
            sendDataToDb()
        } else {
            sendOfflineDataToRecyclerView()
        }

    }

    private fun sendOnlineDataToRecyclerView() {
        postsListViewModel.getPosts()?.observe(this, Observer { posts ->
            posts?.let { postsAdapter?.setPosts(it) }
        })

    }

    private fun sendDataToDb() {
        postsListViewModel.insertAllPosts()

    }

    private fun sendOfflineDataToRecyclerView() {
        //postsViewModel.getAllPosts(mappedRoomList)

    }




    override fun onPostItemClick(position: Int) {
        //val post = postsAdapter!!.getItemId(position)
        val userId: Int = postsList[position]!!.userId
        val title: String = postsList[position]!!.title
        val body: String = postsList[position]!!.body
        showPostsDetails(userId, title, body)
    }

    fun showPostsDetails(userId: Int, title: String, body: String) {
        val intent = Intent(this, PostsDetailsActivity::class.java)
        intent.putExtra("POST_USER_ID", userId)
        intent.putExtra("POST_TITLE", title)
        intent.putExtra("POST_BODY", body)
        startActivity(intent)
    }



    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }


}

