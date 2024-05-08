package com.android_test_luizalabs.data.datasource

import com.android_test_luizalabs.core.extensions.parseHttpError
import com.android_test_luizalabs.data.api.GistService
import com.android_test_luizalabs.data.mappers.toDomain
import com.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GistRemoteDataSourceImpl(private val gistService: GistService) : GistRemoteDataSource {

    override fun getGists(): Flow<List<Gist>> = flow {
        emit(
            gistService.getGist().map { it.toDomain() }
        )
    }.parseHttpError()
}