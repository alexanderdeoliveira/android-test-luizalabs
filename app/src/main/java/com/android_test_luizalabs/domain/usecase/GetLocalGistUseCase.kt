package com.android_test_luizalabs.domain.usecase

import com.android_test_luizalabs.domain.model.Gist
import kotlinx.coroutines.flow.Flow

interface GetLocalGistUseCase {

    operator fun invoke(): Flow<List<Gist>>
}