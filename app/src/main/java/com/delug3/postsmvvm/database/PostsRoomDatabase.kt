package com.delug3.postsmvvm.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delug3.postsmvvm.database.dao.PostsDao
import com.delug3.postsmvvm.model.Posts
import com.delug3.postsmvvm.postslist.PostsListViewModel
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Posts::class], version = 1)

abstract class PostsRoomDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {
        @Volatile
        private var INSTANCE: PostsRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PostsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                    PostsRoomDatabase::class.java,
                    "posts_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
    }



