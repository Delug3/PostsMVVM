package com.delug3.postsmvvm.network

import com.delug3.postsmvvm.model.Comments
import com.delug3.postsmvvm.model.Posts
import com.delug3.postsmvvm.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApiInterface {
    // @get:GET("posts")
    // val getPosts: Call<List<Posts?>?>?

    @GET("posts")
    suspend fun getPosts(): List<Posts>

    //users/1
    @GET("users/{id}")
    suspend fun getUserName(@Path(value = "id") userId: Int): User

    //comments?postId=1
    @GET("comments?")
    suspend fun getComments(@Query("postId") postId: Int): List<Comments>

    //@GET("comments?")
    //fun getComments(@Query("postId") postId: Int): Call<List<Comments?>?>?
}