package com.delug3.postsmvvm.data.persistence.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.delug3.postsmvvm.data.persistence.dao.PostsDao
import com.delug3.postsmvvm.data.model.Posts

class PostsRepository(private val postsDao: PostsDao) {

    val allPosts: LiveData<List<Posts>> = postsDao.getPosts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertPosts(postsRoom: Posts) {
        postsDao.insertPost(postsRoom)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllPosts(postsRoomList: List<Posts?>?) {
        postsDao.insertAllPosts(postsRoomList)
    }


    //users

    //comments
}