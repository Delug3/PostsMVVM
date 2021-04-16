package com.delug3.postsmvvm.data.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delug3.postsmvvm.data.model.Posts

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Posts)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(posts: List<Posts?>?)

    @Query("SELECT * FROM posts_table")
    fun getPosts(): LiveData<List<Posts>>


}