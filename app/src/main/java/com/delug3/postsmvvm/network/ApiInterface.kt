package com.delug3.postsmvvm.network

import com.delug3.postsmvvm.model.Comments
import com.delug3.postsmvvm.model.Posts
import com.delug3.postsmvvm.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @get:GET("posts")
    val posts: Call<List<Posts?>?>?


    //users/1
     @GET("users/{id}")
    fun getUserName(@Path(value = "id", encoded = true) id: Int): Call<User?>?


    //comments?postId=1
    @GET("comments?")
    fun getComments(@Query("postId") postId: Int): Call<List<Comments?>?>?

}