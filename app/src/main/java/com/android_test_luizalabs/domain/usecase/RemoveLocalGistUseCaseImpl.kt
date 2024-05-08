package com.android_test_luizalabs.domain.usecase

import com.android_test_luizalabs.domain.repository.GistRepository

class RemoveLocalGistUseCaseImpl(private val repository: GistRepository) :
    RemoveLocalGistUseCase {
    override suspend fun invoke(id: String) {
        repository.removeLocalGist(id)
    }
}