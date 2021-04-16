package com.delug3.postsmvvm.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delug3.postsmvvm.ui.main.viewmodels.PostsDetailsViewModel


class PostsViewModelFactory(private val mParam: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsDetailsViewModel(mParam) as T
    }

    }



