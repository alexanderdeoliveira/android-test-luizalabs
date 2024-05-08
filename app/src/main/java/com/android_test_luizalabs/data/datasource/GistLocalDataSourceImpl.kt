package com.android_test_luizalabs.data.datasource

import com.android_test_luizalabs.data.local.GistDao
import com.android_test_luizalabs.data.mappers.toDomain
import com.android_test_luizalabs.data.mappers.toEntity
import com.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GistLocalDataSourceImpl(private val gistDao: GistDao) : GistLocalDataSource {
    override fun getGists(): Flow<List<Gist>> = flow {
        emit(gistDao.getGists().map { it.toDomain() })
    }

    override suspend fun deleteGist(id: String) {
        gistDao.setUnfavoriteGist(id)
    }

    override suspend fun setGist(gist: Gist) {
        gistDao.saveUser(gist.toEntity())
    }
}