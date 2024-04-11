package com.yagosouza.android_test_luizalabs.data.datasource

import com.yagosouza.android_test_luizalabs.data.local.GistDao
import com.yagosouza.android_test_luizalabs.data.mappers.toDomain
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GistLocalDataSourceImpl(private val gistDao: GistDao) : GistLocalDataSource {
    override fun getGists(): Flow<List<Gist>> = flow {
        emit(gistDao.getGists().map { it.toDomain() })
    }

    override fun getGist(id: String): Flow<Gist> = flow {
        emit(gistDao.getGistById(id).toDomain())
    }
}