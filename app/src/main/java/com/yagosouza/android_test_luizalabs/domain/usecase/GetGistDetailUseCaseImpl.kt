package com.yagosouza.android_test_luizalabs.domain.usecase

import com.yagosouza.android_test_luizalabs.domain.model.GistDetail
import com.yagosouza.android_test_luizalabs.domain.repository.GistDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGistDetailUseCaseImpl(private val repository: GistDetailRepository) :
    GetGistDetailUseCase {

    override fun invoke(id: String): Flow<GistDetail> =
        repository.getGistDetail(id)
}