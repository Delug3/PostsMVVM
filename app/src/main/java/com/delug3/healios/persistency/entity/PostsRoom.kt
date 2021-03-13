package com.delug3.healios.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
class PostsRoom(

    @PrimaryKey
    @ColumnInfo(name = "userID")
    val userID: Long,
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String,
)


//Users


//Comments