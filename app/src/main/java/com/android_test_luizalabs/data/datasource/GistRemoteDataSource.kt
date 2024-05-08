package com.android_test_luizalabs.data.datasource

import com.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow

interface GistRemoteDataSource {

    fun getGists(): Flow<List<Gist>>
}