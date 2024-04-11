package com.yagosouza.android_test_luizalabs.data.mappers

import com.yagosouza.android_test_luizalabs.data.local.GistEntity
import com.yagosouza.android_test_luizalabs.data.mappers.FilesMapper.mapToDomain
import com.yagosouza.android_test_luizalabs.data.model.FilesResponse
import com.yagosouza.android_test_luizalabs.data.model.GistResponse
import com.yagosouza.android_test_luizalabs.data.model.OwnerResponse
import com.yagosouza.android_test_luizalabs.domain.model.Files
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.domain.model.Owner

fun OwnerResponse.toDomain() = Owner(login = login, avatarUrl = avatar_url)

fun GistResponse.toDomain() =
    Gist(id = id, files = mapToDomain(files), description = description, owner = owner.toDomain())

fun GistEntity.toDomain() =
    Gist(
        id = id,
        files = Files(type = type),
        description = description,
        owner = Owner(login = login, avatarUrl = avatarUrl)
    )

fun Gist.toEntity() = GistEntity(
    id = id!!, //TODO verificar
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