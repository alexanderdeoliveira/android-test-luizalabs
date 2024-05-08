package com.android_test_luizalabs.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GistData")
data class GistEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo
    val type: String?,

    @ColumnInfo
    val description: String?,

    @ColumnInfo
    val login: String?,

    @ColumnInfo
    val avatarUrl: String?,

    @ColumnInfo
    val isFavorite: Boolean = true
)