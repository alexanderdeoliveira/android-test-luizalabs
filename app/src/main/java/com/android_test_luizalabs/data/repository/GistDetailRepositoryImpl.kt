package com.android_test_luizalabs.data.repository

import com.android_test_luizalabs.data.datasource.GistDetailRemoteDataSource
import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.domain.repository.GistDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GistDetailRepositoryImpl(
    private val gistDetailRemoteDataSource: GistDetailRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    GistDetailRepository {

    override fun getGistDetail(id: String): Flow<Gist> {
        return gistDetailRemoteDataSource.getGistDetail(id)
            .flowOn(dispatcher)
    }
}