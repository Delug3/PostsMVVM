package com.delug3.healios.persistency

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delug3.healios.persistence.entity.PostsRoom
import com.delug3.healios.persistency.dao.PostsDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [PostsRoom::class], version = 1)

abstract class PostsRoomDb : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {
        @Volatile
        private var INSTANCE: PostsRoomDb? = null

        fun getDb(
            context: Context,
            scope: CoroutineScope
        ): PostsRoomDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostsRoomDb::class.java,
                    "posts_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}