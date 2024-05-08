package com.android_test_luizalabs.stubs

import com.android_test_luizalabs.domain.model.Files
import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.domain.model.Owner

internal fun getSubGistList(): List<Gist> {
    return listOf(getSubGist(), getSubGist(), getSubGist(), getSubGist(), getSubGist())
}

internal fun getSubGist(): Gist {
    return Gist(
        id = "c56c877e90117ee111f04b7e6e203e26",
        files = Files(
            type = "text/plain"
        ),
        description = "description",
        owner = Owner(
            login = "userName",
            avatarUrl = "avatar"
        )
    )
}