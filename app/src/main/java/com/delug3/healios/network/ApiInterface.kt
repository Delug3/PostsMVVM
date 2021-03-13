package com.delug3.healios.network

import com.delug3.healios.model.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @get:GET("posts")
    val posts: Call<List<Posts?>?>?


    //posts(id)--->user id and comments in second activity
    // @GET("user/{id}")
    // fun getUser(@Path(value = "id", encoded = true) id: Long): Call<User?>?

    // @GET("comments/{id}")
    // fun getComments(@Path(value = "id", encoded = true) id: String): Call<Comments?>?
}