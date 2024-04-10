package com.yagosouza.android_test_luizalabs.domain.usecase

import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.domain.repository.GistDetailRepository
import kotlinx.coroutines.flow.Flow

class GetGistDetailUseCaseImpl(private val repository: GistDetailRepository) :
    GetGistDetailUseCase {

    override fun invoke(id: String): Flow<Gist> =
        repository.getGistDetail(id)
}