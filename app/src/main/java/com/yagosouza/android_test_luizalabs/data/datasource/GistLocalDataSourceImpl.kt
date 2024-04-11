package com.yagosouza.android_test_luizalabs.data.datasource

import com.yagosouza.android_test_luizalabs.data.local.GistDao
import com.yagosouza.android_test_luizalabs.data.mappers.toDomain
import com.yagosouza.android_test_luizalabs.data.mappers.toEntity
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GistLocalDataSourceImpl(private val gistDao: GistDao) : GistLocalDataSource {
    override fun getGists(): Flow<List<Gist>> = flow {
        emit(gistDao.getGists().map { it.toDomain() })
    }

    override suspend fun deleteGist(id: String) {
        gistDao.deleteGist(id)
    }

    override suspend fun setGist(gist: Gist) {
        gistDao.saveUser(gist.toEntity())
    }
}