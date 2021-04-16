package com.delug3.postsmvvm.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.delug3.postsmvvm.database.dao.PostsDao
import com.delug3.postsmvvm.model.Posts

class PostsRepository(private val postsDao: PostsDao) {

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