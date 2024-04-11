package com.yagosouza.android_test_luizalabs.data.mappers

import com.yagosouza.android_test_luizalabs.data.local.FilesEntity
import com.yagosouza.android_test_luizalabs.data.local.GistEntity
import com.yagosouza.android_test_luizalabs.data.local.OwnerEntity
import com.yagosouza.android_test_luizalabs.data.mappers.FilesMapper.mapToDomain
import com.yagosouza.android_test_luizalabs.data.model.FilesResponse
import com.yagosouza.android_test_luizalabs.data.model.GistResponse
import com.yagosouza.android_test_luizalabs.data.model.OwnerResponse
import com.yagosouza.android_test_luizalabs.domain.model.Files
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.domain.model.Owner

fun OwnerResponse.toDomain() = Owner(login = login, avatarUrl = avatar_url)

fun OwnerEntity.toDomain() = Owner(login = login, avatarUrl = avatarUrl)

fun GistResponse.toDomain() =
    Gist(id = id, files = mapToDomain(files), description = description, owner = owner.toDomain())

fun GistEntity.toDomain() =
    Gist(id = id, files = files?.toDomain(), description = description, owner = owner?.toDomain())

fun FilesEntity.toDomain() = Files(type = type)

object FilesMapper {
    fun mapToDomain(files: Map<String, FilesResponse>): Files {
        val (_, fileDetails) = files.entries.first()
        return Files(
            type = fileDetails.type,
        )
    }
}