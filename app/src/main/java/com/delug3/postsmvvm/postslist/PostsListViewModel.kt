package com.delug3.postsmvvm.postslist



import androidx.lifecycle.*
import com.delug3.postsmvvm.database.PostsRepository
import com.delug3.postsmvvm.model.Posts
import com.delug3.postsmvvm.network.PostsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PostsListViewModel(private val repository: PostsRepository) : ViewModel() {


    //private var postsDao: PostsDao = PostsRoomDb.getDb(this, viewModelScope).postsDao()
    private var postsList: MutableLiveData<List<Posts>>? = null


    //doing network call with coroutine
    fun getPosts(): MutableLiveData<List<Posts>>? {
        if (postsList == null) {
            postsList = MutableLiveData<List<Posts>>()
            viewModelScope.launch {
                postsList?.value = PostsApi.retrofitService.getPosts()
            }
        }
        return postsList
    }


    //get data from postList instead of doing second call
    fun insertAllPosts() = viewModelScope.launch(Dispatchers.IO) {
        val test = PostsApi.retrofitService.getPosts()
        repository.insertAllPosts(test)
    }


    class PostsViewModelFactory(private val repository: PostsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostsListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PostsListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }





/*

    init {
        val postsDao = PostsRoomDatabase.getDatabase(context, viewModelScope).postsDao()
        repository = PostsRepository(postsDao)
    }

    fun getPosts(): MutableLiveData<List<Posts>>? {
        if (postsList == null) {
            postsList = MutableLiveData<List<Posts>>()
            getPostsFromUrl()
        }
        return postsList
    }


    private fun getPostsFromUrl() {

        val service = PostsApiService.client?.create(PostsApiInterface::class.java)
        val call = service?.posts

        call!!.enqueue(object : Callback<List<Posts?>?> {
            override fun onResponse(call: Call<List<Posts?>?>, response: Response<List<Posts?>?>) {
                if (response.isSuccessful) {
                    //val result = response.body()

                    postsList?.value = response.body() as List<Posts>?
                    //improve this line
                    mappedRoomList = response.body()?.map {
                        it?.let { it1 ->
                            PostsRoom(
                                it1.userId,
                                it.id,
                                it.title,
                                it.body
                            )
                        }!!
                    }

                    // postsDao.insertAllPosts(mappedRoomList)

                } else {
                    Log.e(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<List<Posts?>?>, t: Throwable) {
                Log.e(TAG, t.toString())
            }
        })
    }
    */



}