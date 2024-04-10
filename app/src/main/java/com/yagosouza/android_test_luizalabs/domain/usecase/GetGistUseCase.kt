package com.yagosouza.android_test_luizalabs.domain.usecase

import com.yagosouza.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow

interface GetGistUseCase {

    operator fun invoke(page: Int): Flow<List<Gist>>
}