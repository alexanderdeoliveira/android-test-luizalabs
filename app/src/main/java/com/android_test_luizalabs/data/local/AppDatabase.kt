package com.android_test_luizalabs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gistDao(): GistDao

}