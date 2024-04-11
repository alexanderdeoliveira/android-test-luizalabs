package com.yagosouza.android_test_luizalabs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        GistEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gistDao(): GistDao

}