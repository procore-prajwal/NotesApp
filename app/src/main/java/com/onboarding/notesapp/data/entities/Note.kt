package com.onboarding.notesapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("content")
    val content: String,

    @ColumnInfo("isLiked")
    val isLiked: Boolean = false,

    @ColumnInfo("createdAt")
    val createdAt: Long = System.currentTimeMillis()
)