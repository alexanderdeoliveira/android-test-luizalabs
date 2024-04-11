package com.yagosouza.android_test_luizalabs.domain.usecase

import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.domain.repository.GistRepository
import kotlinx.coroutines.flow.Flow

class GetLocalGistUseCaseImpl(private val repository: GistRepository) : GetLocalGistUseCase {

    override fun invoke(): Flow<List<Gist>> = repository.getLocalGists()

}