package com.yagosouza.android_test_luizalabs.di

import com.yagosouza.android_test_luizalabs.data.api.GistService
import com.yagosouza.android_test_luizalabs.data.datasource.GistDetailRemoteDataSource
import com.yagosouza.android_test_luizalabs.data.datasource.GistDetailRemoteDataSourceImpl
import com.yagosouza.android_test_luizalabs.data.datasource.GistRemoteDataSource
import com.yagosouza.android_test_luizalabs.data.datasource.GistRemoteDataSourceImpl
import com.yagosouza.android_test_luizalabs.data.repository.GistDetailRepositoryImpl
import com.yagosouza.android_test_luizalabs.data.repository.GistRepositoryImpl
import com.yagosouza.android_test_luizalabs.domain.repository.GistDetailRepository
import com.yagosouza.android_test_luizalabs.domain.repository.GistRepository
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistDetailUseCase
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistDetailUseCaseImpl
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistUseCase
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistUseCaseImpl
import com.yagosouza.android_test_luizalabs.helper.network.Service
import com.yagosouza.android_test_luizalabs.presentation.detail.DetailPresenterImpl
import com.yagosouza.android_test_luizalabs.presentation.list.ListPresenterImpl
import org.koin.dsl.module

val appModule = module {
    single { Service().createService(GistService::class.java) }

    factory<GistRemoteDataSource> { GistRemoteDataSourceImpl(gistService = get()) }
    factory<GistDetailRemoteDataSource> { GistDetailRemoteDataSourceImpl(gistService = get()) }

    factory<GistRepository> { GistRepositoryImpl(gistRemoteDataSource = get()) }
    factory<GistDetailRepository> { GistDetailRepositoryImpl(gistDetailRemoteDataSource = get()) }

    factory<GetGistUseCase> { GetGistUseCaseImpl(repository = get()) }
    factory<GetGistDetailUseCase> { GetGistDetailUseCaseImpl(repository = get()) }

    factory { ListPresenterImpl(getGistUseCase = get()) }

    factory { DetailPresenterImpl(getGistDetailUseCase = get()) }
}