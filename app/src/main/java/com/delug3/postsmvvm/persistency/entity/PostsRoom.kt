package com.delug3.postsmvvm.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
class PostsRoom(

    @PrimaryKey
    @ColumnInfo(name = "userID")
    val userID: Int,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String,
)


//Users


//Comments