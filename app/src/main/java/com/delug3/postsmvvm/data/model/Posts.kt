package com.delug3.postsmvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class Posts(

    val userId: Int,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val title: String,
    val body: String

)