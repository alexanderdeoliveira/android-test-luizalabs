package com.android_test_luizalabs.domain.usecase

import com.android_test_luizalabs.domain.model.Gist

interface SetLocalGistUseCase {

    suspend operator fun invoke(gist: Gist)
}