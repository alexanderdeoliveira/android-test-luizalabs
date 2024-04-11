package com.yagosouza.android_test_luizalabs.domain.usecase

import com.yagosouza.android_test_luizalabs.domain.model.Gist

interface SetLocalGistUseCase {

    suspend operator fun invoke(gist: Gist)
}