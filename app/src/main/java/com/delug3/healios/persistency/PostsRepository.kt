package com.delug3.healios.persistency

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.delug3.healios.persistence.entity.PostsRoom
import com.delug3.healios.persistency.dao.PostsDao

class PostsRepository(private val postsDao: PostsDao) {

    val allPosts: LiveData<List<PostsRoom>> = postsDao.readAllPosts()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllPosts(postsRoomList: List<PostsRoom?>?) {
        postsDao.insertAllPosts(postsRoomList)
    }


    //users

    //comments
}