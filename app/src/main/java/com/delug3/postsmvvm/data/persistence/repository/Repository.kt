package com.delug3.postsmvvm.data.persistence.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.delug3.postsmvvm.data.persistence.dao.PostsDao
import com.delug3.postsmvvm.data.model.Posts

class Repository(private val postsDao: PostsDao) {

    val allPosts: LiveData<List<Posts>> = postsDao.readAllPosts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertPosts(postsRoom: Posts) {
        postsDao.insertPosts(postsRoom)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllPosts(postsRoomList: List<Posts?>?) {
        postsDao.insertAllPosts(postsRoomList)
    }


    //users

    //comments
}