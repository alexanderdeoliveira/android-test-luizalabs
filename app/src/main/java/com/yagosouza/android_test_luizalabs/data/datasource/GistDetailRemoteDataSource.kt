package com.yagosouza.android_test_luizalabs.data.datasource

import com.yagosouza.android_test_luizalabs.domain.model.GistDetail
import kotlinx.coroutines.flow.Flow

interface GistDetailRemoteDataSource {

    fun getGistDetail(id: String): Flow<GistDetail>
}