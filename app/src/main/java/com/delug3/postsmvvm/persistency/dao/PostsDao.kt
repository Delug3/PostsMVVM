package com.delug3.postsmvvm.persistency.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delug3.postsmvvm.persistence.entity.PostsRoom

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(postsRoomList: List<PostsRoom?>?);

    @Query("SELECT * FROM posts_table")
    fun readAllPosts(): LiveData<List<PostsRoom>>


}