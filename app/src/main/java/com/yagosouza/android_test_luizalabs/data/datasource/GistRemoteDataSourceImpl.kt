package com.yagosouza.android_test_luizalabs.data.datasource

import com.yagosouza.android_test_luizalabs.core.extensions.parseHttpError
import com.yagosouza.android_test_luizalabs.data.api.GistService
import com.yagosouza.android_test_luizalabs.data.mappers.toDomain
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GistRemoteDataSourceImpl(private val gistService: GistService) : GistRemoteDataSource {

    override fun getGists(page: Int): Flow<List<Gist>> = flow {
        emit(
            gistService.getGist(page).map { it.toDomain() }
        )
    }.parseHttpError()
}