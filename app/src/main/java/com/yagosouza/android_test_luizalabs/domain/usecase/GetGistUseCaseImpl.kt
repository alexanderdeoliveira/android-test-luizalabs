package com.yagosouza.android_test_luizalabs.domain.usecase

import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.domain.repository.GistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGistUseCaseImpl(private val repository: GistRepository) : GetGistUseCase {

    override fun invoke(page: Int): Flow<List<Gist>> =
        repository.getGists(page)
}