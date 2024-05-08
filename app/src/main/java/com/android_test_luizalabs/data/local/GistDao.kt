package com.android_test_luizalabs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(gistEntity: GistEntity)

    @Query("SELECT * FROM GistData WHERE id = :id")
    suspend fun getGistById(id: String): GistEntity

    @Query("SELECT * FROM GistData")
    suspend fun getGists(): List<GistEntity>

    @Query("UPDATE GistData SET isFavorite = 0 WHERE id = :id")
    suspend fun setUnfavoriteGist(id: String)
}