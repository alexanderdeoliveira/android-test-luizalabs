package com.yagosouza.android_test_luizalabs.domain.usecase

interface RemoveLocalGistUseCase {

    suspend operator fun invoke(id: String)
}