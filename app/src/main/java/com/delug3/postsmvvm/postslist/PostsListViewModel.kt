package com.delug3.postsmvvm.postslist


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delug3.postsmvvm.model.Posts
import com.delug3.postsmvvm.network.ApiClient
import com.delug3.postsmvvm.network.ApiInterface
import com.delug3.postsmvvm.persistence.entity.PostsRoom
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostsListViewModel: ViewModel()  {
    private val TAG = "POSTS"
    //private var postsDao: PostsDao = PostsRoomDb.getDb(this, viewModelScope).postsDao()

    private var postsList: MutableLiveData<List<Posts>>? = null
    private var mappedRoomList: List<PostsRoom>? = null


    private fun getPostsFromUrl() {

        val service = ApiClient.client?.create(ApiInterface::class.java)
        val call = service?.posts

        call!!.enqueue(object : Callback<List<Posts?>?> {
            override fun onResponse(call: Call<List<Posts?>?>, response: Response<List<Posts?>?>) {
                if (response.isSuccessful) {
                    //val result = response.body()

                    postsList?.value = response.body() as List<Posts>?
                    //improve this line
                    mappedRoomList = response.body()?.map {
                        it?.let { it1 ->
                            PostsRoom(
                                it1.userId,
                                it.id,
                                it.title,
                                it.body
                            )
                        }!!
                    }

                    // postsDao.insertAllPosts(mappedRoomList)

                } else {
                    Log.e(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<List<Posts?>?>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        })
    }

    fun getPosts(): MutableLiveData<List<Posts>>? {
        if (postsList == null) {
            postsList = MutableLiveData<List<Posts>>()
            getPostsFromUrl()
        }
        return postsList
    }

    private var _currentPost = "Post Test"
    val currentPost: String
        get() = _currentPost
}