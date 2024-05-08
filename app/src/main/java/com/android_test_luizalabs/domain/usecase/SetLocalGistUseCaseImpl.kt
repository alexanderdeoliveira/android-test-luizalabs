package com.android_test_luizalabs.domain.usecase

import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.domain.repository.GistRepository

class SetLocalGistUseCaseImpl(
    private val repository: GistRepository
) : SetLocalGistUseCase {

    override suspend fun invoke(gist: Gist) {
        repository.setGists(gist)
    }
}