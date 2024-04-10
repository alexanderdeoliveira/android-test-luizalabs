package com.yagosouza.android_test_luizalabs.data.mappers

import com.yagosouza.android_test_luizalabs.data.mappers.FilesMapper.mapToDomain
import com.yagosouza.android_test_luizalabs.data.model.FilesResponse
import com.yagosouza.android_test_luizalabs.data.model.GistDetailResponse
import com.yagosouza.android_test_luizalabs.data.model.GistResponse
import com.yagosouza.android_test_luizalabs.data.model.OwnerResponse
import com.yagosouza.android_test_luizalabs.domain.model.Files
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.domain.model.GistDetail
import com.yagosouza.android_test_luizalabs.domain.model.Owner

fun OwnerResponse.toDomain() = Owner(login = login, avatarUrl = avatar_url)

fun GistResponse.toDomain() =
    Gist(id = id, files = mapToDomain(files), description = description, owner = owner.toDomain())

fun GistDetailResponse.toDomain() = GistDetail(id = id, description = description, detail = detail)

object FilesMapper {
    fun mapToDomain(files: Map<String, FilesResponse>): Files {
        val (_, fileDetails) = files.entries.first()
        return Files(
            type = fileDetails.type,
        )
    }
}