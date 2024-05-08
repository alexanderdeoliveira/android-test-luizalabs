package com.android_test_luizalabs.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GistResponse(
    @SerialName("id")
    val id: String,

    @SerialName("files")
    val files: Map<String, FilesResponse>,

    @SerialName("description")
    val description: String,

    @SerialName("owner")
    val owner: OwnerResponse
)

@Serializable
data class FilesResponse(
    @SerialName("type")
    val type: String,
)

@Serializable
data class OwnerResponse(
    @SerialName("login")
    val login: String,

    @SerialName("avatar_url")
    val avatar_url: String
)