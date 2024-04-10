package com.yagosouza.android_test_luizalabs.data.repository

import com.yagosouza.android_test_luizalabs.data.api.GistService
import com.yagosouza.android_test_luizalabs.data.datasource.GistDetailRemoteDataSource
import com.yagosouza.android_test_luizalabs.data.mappers.toDomain
import com.yagosouza.android_test_luizalabs.domain.model.GistDetail
import com.yagosouza.android_test_luizalabs.domain.repository.GistDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GistDetailRepositoryImpl(
    private val gistDetailRemoteDataSource: GistDetailRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    GistDetailRepository {

    override fun getGistDetail(id: String): Flow<GistDetail> {
        return gistDetailRemoteDataSource.getGistDetail(id)
            .flowOn(dispatcher)
    }
}