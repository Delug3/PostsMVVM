package com.delug3.postsmvvm.ui.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delug3.postsmvvm.data.model.Comments
import com.delug3.postsmvvm.data.api.PostsApi
import kotlinx.coroutines.launch


class PostsDetailsViewModel(mParam: Int) : ViewModel() {
    private var queryId: Int = mParam
    private var userName : MutableLiveData<String>? = null
    private var commentsList: MutableLiveData<List<Comments>>? = null


    //doing network call with coroutine
    fun fetchUserName(): MutableLiveData<String>? {
        if (userName == null) {
            userName = MutableLiveData<String>()
            viewModelScope.launch {
                val response = PostsApi.RETROFIT_SERVICE.getUserName(queryId)
                userName?.value = response.username
            }
        }
        return userName
    }

    //doing network call with coroutine
    fun fetchComments(): MutableLiveData<List<Comments>>? {
        if (commentsList == null) {
            commentsList = MutableLiveData<List<Comments>>()
            viewModelScope.launch {
                commentsList?.value = PostsApi.RETROFIT_SERVICE.getComments(queryId)
            }
        }
        return commentsList
    }

}

    /*
    private fun getUserFromEndpoint() {

        val service = PostsApiService.client?.create(PostsApiInterface::class.java)
        val call = service?.getUserName(queryId)

        call!!.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful) {

                    userName?.value = response.body()?.username

                } else {
                    Log.e(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        })
    }



    private fun getCommentsFromEndpoint() {

        val service = PostsApiService.client?.create(PostsApiInterface::class.java)
        val call = service?.getComments(queryId)

        call!!.enqueue(object : Callback<List<Comments?>?> {
            override fun onResponse(call: Call<List<Comments?>?>, response: Response<List<Comments?>?>) {
                if (response.isSuccessful) {

                    commentsList?.value = response.body() as List<Comments>?

                } else {
                    Log.e(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<List<Comments?>?>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        })
    }


   fun getUserName(): LiveData<String>? {
       if (userName == null) {
           userName = MutableLiveData<String>()
           getUserFromEndpoint()
       }
       return userName
    }


    fun getComments(): MutableLiveData<List<Comments>>? {
        if (commentsList == null) {
            commentsList = MutableLiveData<List<Comments>>()
            getCommentsFromEndpoint()
        }
        return commentsList
    }

    */

