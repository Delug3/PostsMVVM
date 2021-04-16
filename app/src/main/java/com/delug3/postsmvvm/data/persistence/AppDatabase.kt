package com.delug3.postsmvvm.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delug3.postsmvvm.data.persistence.dao.PostsDao
import com.delug3.postsmvvm.data.model.Posts
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Posts::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                    AppDatabase::class.java,
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



