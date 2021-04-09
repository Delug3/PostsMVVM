package com.delug3.postsmvvm.postslist



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delug3.postsmvvm.model.Posts
import com.delug3.postsmvvm.network.PostsApi


import com.delug3.postsmvvm.persistence.entity.PostsRoom
import kotlinx.coroutines.launch


class PostsListViewModel : ViewModel() {
    //private var postsDao: PostsDao = PostsRoomDb.getDb(this, viewModelScope).postsDao()
    private var postsList: MutableLiveData<List<Posts>>? = null
    private var mappedRoomList: List<PostsRoom>? = null


    //doing network call with coroutine
    fun getPosts(): MutableLiveData<List<Posts>>? {
        if (postsList == null) {
            postsList = MutableLiveData<List<Posts>>()
            viewModelScope.launch {
                postsList?.value = PostsApi.retrofitService.getPosts()
            }
        }
        return postsList
    }

/*
    fun getPosts(): MutableLiveData<List<Posts>>? {
        if (postsList == null) {
            postsList = MutableLiveData<List<Posts>>()
            getPostsFromUrl()
        }
        return postsList
    }


    private fun getPostsFromUrl() {

        val service = PostsApiService.client?.create(PostsApiInterface::class.java)
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
    */



}