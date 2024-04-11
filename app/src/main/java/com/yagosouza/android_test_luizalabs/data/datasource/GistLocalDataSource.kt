package com.yagosouza.android_test_luizalabs.data.datasource

import com.yagosouza.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow

interface GistLocalDataSource {

    fun getGists(): Flow<List<Gist>>

    suspend fun deleteGist(id: String)

    suspend fun setGist(gist: Gist)
}