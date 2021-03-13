package com.delug3.healios.postslist


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.delug3.healios.model.Posts
import com.delug3.healios.network.ApiClient
import com.delug3.healios.network.ApiInterface
import com.delug3.healios.persistency.PostsRoomDb
import com.delug3.healios.persistence.entity.PostsRoom
import com.delug3.healios.persistency.dao.PostsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostsListViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "POSTS"
    private var postsDao: PostsDao = PostsRoomDb.getDb(application, viewModelScope).postsDao()

    private var postsList: MutableLiveData<List<Posts>>? = null
    private var mappedRoomList: List<PostsRoom>? = null

    fun getPosts(): MutableLiveData<List<Posts>>? {
        if (postsList == null) {
            postsList = MutableLiveData<List<Posts>>()
            getDataFromUrl()
        }
        return postsList
    }


    private fun getDataFromUrl() {

        val service = ApiClient.client?.create(ApiInterface::class.java)
        val call = service?.posts

        call!!.enqueue(object : Callback<List<Posts?>?> {
            override fun onResponse(call: Call<List<Posts?>?>, response: Response<List<Posts?>?>) {
                if (response.isSuccessful) {
                    val result = response.body()

                    postsList?.setValue(result as List<Posts>?);

                    //improve this line
                    mappedRoomList = result?.map {
                        it?.let { it1 ->
                            PostsRoom(
                                it1.userID,
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

}