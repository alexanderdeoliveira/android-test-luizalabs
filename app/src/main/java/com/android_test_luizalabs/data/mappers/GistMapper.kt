package com.android_test_luizalabs.data.mappers

import com.android_test_luizalabs.data.local.GistEntity
import com.android_test_luizalabs.data.mappers.FilesMapper.mapToDomain
import com.android_test_luizalabs.data.model.FilesResponse
import com.android_test_luizalabs.data.model.GistResponse
import com.android_test_luizalabs.data.model.OwnerResponse
import com.android_test_luizalabs.domain.model.Files
import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.domain.model.Owner

fun OwnerResponse.toDomain() = Owner(login = login, avatarUrl = avatar_url)

fun GistResponse.toDomain() =
    Gist(id = id, files = mapToDomain(files), description = description, owner = owner.toDomain())

fun GistEntity.toDomain() =
    Gist(
        id = id,
        files = Files(type = type),
        description = description,
        owner = Owner(login = login, avatarUrl = avatarUrl),
        isFavorite = isFavorite
    )

fun Gist.toEntity() = GistEntity(
    id = id,
    type = files?.type,
    description = description,
    login = owner?.login,
    avatarUrl = owner?.avatarUrl,
)

object FilesMapper {
    fun mapToDomain(files: Map<String, FilesResponse>): Files {
        val (_, fileDetails) = files.entries.first()
        return Files(
            type = fileDetails.type,
        )
    }
}