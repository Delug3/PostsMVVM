package com.delug3.postsmvvm.postslistdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delug3.postsmvvm.model.Comments
import com.delug3.postsmvvm.model.User
import com.delug3.postsmvvm.network.ApiClient
import com.delug3.postsmvvm.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostsDetailsViewModel(mParam: Int) : ViewModel() {
    private val TAG = "USER"
    private var queryId: Int = mParam
    //private lateinit var userName: String
    private var userName : MutableLiveData<String>? = null
    private var commentsList: MutableLiveData<List<Comments>>? = null

    private fun getUserFromEndpoint() {

        val service = ApiClient.client?.create(ApiInterface::class.java)
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

        val service = ApiClient.client?.create(ApiInterface::class.java)
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


}