package com.android_test_luizalabs.domain.usecase

import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.domain.repository.GistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGistUseCaseImpl(private val repository: GistRepository) : GetGistUseCase {

    override fun invoke(): Flow<List<Gist>> =
        repository.getGists()
}