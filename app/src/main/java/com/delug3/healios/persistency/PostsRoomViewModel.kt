package com.delug3.healios.persistence

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.delug3.healios.persistence.entity.PostsRoom
import com.delug3.healios.persistency.PostsRepository
import com.delug3.healios.persistency.PostsRoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsRoomViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PostsRepository
    val allPosts: LiveData<List<PostsRoom>>



    init {
        val postsDao = PostsRoomDb.getDb(application, viewModelScope).postsDao()
        repository = PostsRepository(postsDao)
        allPosts = repository.allPosts
    }

    fun insertAllPosts(postsRoomList: List<PostsRoom?>?) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAllPosts(postsRoomList)
    }


    //insert users into...


    //insert comments into...

}