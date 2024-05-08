package com.android_test_luizalabs.domain.repository

import com.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow

interface GistDetailRepository {

    fun getGistDetail(id: String): Flow<Gist>
}