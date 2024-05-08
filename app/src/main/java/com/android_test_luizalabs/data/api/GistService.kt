package com.android_test_luizalabs.data.api

import com.android_test_luizalabs.data.model.GistResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GistService {

    @GET("gists/public")
    suspend fun getGist(): List<GistResponse>

    @GET("gists/{gist_id}")
    suspend fun getGistDetail(
        @Path("gist_id") id: String
    ): GistResponse
}