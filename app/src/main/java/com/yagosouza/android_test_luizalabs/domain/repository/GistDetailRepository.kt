package com.yagosouza.android_test_luizalabs.domain.repository

import com.yagosouza.android_test_luizalabs.domain.model.GistDetail
import kotlinx.coroutines.flow.Flow

interface GistDetailRepository {

    fun getGistDetail(id: String): Flow<GistDetail>
}