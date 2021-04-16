package com.delug3.postsmvvm.data.api

import com.delug3.postsmvvm.data.model.Comments
import com.delug3.postsmvvm.data.model.Posts
import com.delug3.postsmvvm.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsService {
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