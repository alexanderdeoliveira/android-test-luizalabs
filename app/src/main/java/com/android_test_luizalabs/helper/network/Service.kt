package com.android_test_luizalabs.helper.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

class Service {

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun <API> createService(apiClass: Class<API>): API {
        return retrofit.create(apiClass)
    }
}