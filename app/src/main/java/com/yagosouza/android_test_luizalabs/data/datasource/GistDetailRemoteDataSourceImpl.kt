package com.yagosouza.android_test_luizalabs.data.datasource

import com.yagosouza.android_test_luizalabs.data.api.GistService
import com.yagosouza.android_test_luizalabs.data.mappers.toDomain
import com.yagosouza.android_test_luizalabs.domain.model.GistDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GistDetailRemoteDataSourceImpl(private val gistService: GistService) :
    GistDetailRemoteDataSource {

    override fun getGistDetail(id: String): Flow<GistDetail> = flow {
        emit(gistService.getGistDetail(id).toDomain())
    }
}