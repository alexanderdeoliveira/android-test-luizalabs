package com.yagosouza.android_test_luizalabs.domain.repository

import com.yagosouza.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow

interface GistRepository {

    fun getGists(page: Int): Flow<List<Gist>>
    fun getLocalGists(): Flow<List<Gist>>
    suspend fun setGists(gist: Gist)
    suspend fun removeLocalGist(id: String)
}