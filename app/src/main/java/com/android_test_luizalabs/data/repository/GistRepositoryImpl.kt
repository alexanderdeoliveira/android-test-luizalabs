package com.android_test_luizalabs.data.repository

import com.android_test_luizalabs.data.datasource.GistLocalDataSource
import com.android_test_luizalabs.data.datasource.GistRemoteDataSource
import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.domain.repository.GistRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GistRepositoryImpl(
    private val gistRemoteDataSource: GistRemoteDataSource,
    private val gistLocalDataSource: GistLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GistRepository {

    override fun getGists(): Flow<List<Gist>> {
        return gistRemoteDataSource.getGists()
            .flowOn(dispatcher)
    }

    override fun getLocalGists(): Flow<List<Gist>> {
        return gistLocalDataSource.getGists()
            .flowOn(dispatcher)
    }

    override suspend fun setGists(gist: Gist) {
        return gistLocalDataSource.setGist(gist)
    }

    override suspend fun removeLocalGist(id: String) {
        return gistLocalDataSource.deleteGist(id)
    }
}