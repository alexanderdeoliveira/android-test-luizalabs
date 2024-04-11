package com.yagosouza.android_test_luizalabs.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GistData")
data class GistEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String?,

    @ColumnInfo
    val files: FilesEntity?,

    @ColumnInfo
    val description: String?,

    @ColumnInfo
    val owner: OwnerEntity?
)

data class FilesEntity(
    @ColumnInfo
    val type: String?
)

data class OwnerEntity(
    @ColumnInfo
    val login: String?,

    @ColumnInfo
    val avatarUrl: String?
)