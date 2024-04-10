package com.yagosouza.android_test_luizalabs.domain.model

data class Gist(
    val id: String?,
    val files: Files?,
    val description: String?,
    val owner: Owner?
)

data class Files(
    val type: String?
)

data class Owner(
    val login: String?,
    val avatarUrl: String?
)