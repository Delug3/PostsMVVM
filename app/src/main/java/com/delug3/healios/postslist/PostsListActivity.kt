package com.delug3.healios.postslist

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.delug3.healios.adapter.PostsAdapter
import com.delug3.healios.databinding.ActivityListPostsBinding
import com.delug3.healios.model.Posts
import com.delug3.healios.persistence.PostsRoomViewModel
import com.delug3.healios.postlistdetails.PostActivityDetails


class PostsListActivity : AppCompatActivity(), PostItemClickListener {
    private val TAG = "POSTS"

    private lateinit var postsListViewModel: PostsListViewModel
    private lateinit var postsRoomViewModel: PostsRoomViewModel
    lateinit var binding: ActivityListPostsBinding

    private var postsAdapter: PostsAdapter? = null

    var postsList: MutableList<Posts?> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postsListViewModel = ViewModelProvider(this).get(PostsListViewModel::class.java)
        postsRoomViewModel = ViewModelProvider(this).get(PostsRoomViewModel::class.java)
        setUpRecyclerView()

        readData()
    }


    private fun setUpRecyclerView() {
        postsAdapter = PostsAdapter(this, postsList)
        binding.recyclerViewPosts.adapter = postsAdapter
        binding.recyclerViewPosts.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPosts.layoutManager = layoutManager
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

    private fun sendOfflineDataToRecyclerView() {
        //postsViewModel.getAllPosts(mappedRoomList)

    }

    private fun sendDataToDb() {
        // postsRoomViewModel.insertAllPosts()

    }


    override fun onPostItemClick(position: Int) {
        val post = postsAdapter!!.getItemId(position)

    }

    /*override fun showPoiDetails() {
        val intent = Intent(this.PostsListActivity, PostActivityDetails::class.java)
        intent.putExtra("POST_TITLE", title)
        intent.putExtra("POST_BODY", body)

        //send user and comments data

        startActivity(intent)
    }

     */

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

