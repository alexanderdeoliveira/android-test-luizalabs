package com.yagosouza.android_test_luizalabs.data.datasource

import com.yagosouza.android_test_luizalabs.core.extensions.parseHttpError
import com.yagosouza.android_test_luizalabs.data.api.GistService
import com.yagosouza.android_test_luizalabs.data.mappers.toDomain
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GistDetailRemoteDataSourceImpl(private val gistService: GistService) :
    GistDetailRemoteDataSource {

    override fun getGistDetail(id: String): Flow<Gist> = flow {
        emit(
            gistService.getGistDetail(id).toDomain()
        )
    }.parseHttpError()
}