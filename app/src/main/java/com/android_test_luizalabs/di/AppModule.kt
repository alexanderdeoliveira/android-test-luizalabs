package com.android_test_luizalabs.di

import android.app.Application
import androidx.room.Room
import com.android_test_luizalabs.data.api.GistService
import com.android_test_luizalabs.data.datasource.GistDetailRemoteDataSource
import com.android_test_luizalabs.data.datasource.GistDetailRemoteDataSourceImpl
import com.android_test_luizalabs.data.datasource.GistLocalDataSource
import com.android_test_luizalabs.data.datasource.GistLocalDataSourceImpl
import com.android_test_luizalabs.data.datasource.GistRemoteDataSource
import com.android_test_luizalabs.data.datasource.GistRemoteDataSourceImpl
import com.android_test_luizalabs.data.local.AppDatabase
import com.android_test_luizalabs.data.local.GistDao
import com.android_test_luizalabs.data.repository.GistDetailRepositoryImpl
import com.android_test_luizalabs.data.repository.GistRepositoryImpl
import com.android_test_luizalabs.domain.repository.GistDetailRepository
import com.android_test_luizalabs.domain.repository.GistRepository
import com.android_test_luizalabs.domain.usecase.GetGistDetailUseCase
import com.android_test_luizalabs.domain.usecase.GetGistDetailUseCaseImpl
import com.android_test_luizalabs.domain.usecase.GetGistUseCase
import com.android_test_luizalabs.domain.usecase.GetGistUseCaseImpl
import com.android_test_luizalabs.domain.usecase.GetLocalGistUseCase
import com.android_test_luizalabs.domain.usecase.GetLocalGistUseCaseImpl
import com.android_test_luizalabs.domain.usecase.RemoveLocalGistUseCase
import com.android_test_luizalabs.domain.usecase.RemoveLocalGistUseCaseImpl
import com.android_test_luizalabs.domain.usecase.SetLocalGistUseCase
import com.android_test_luizalabs.domain.usecase.SetLocalGistUseCaseImpl
import com.android_test_luizalabs.helper.network.Service
import com.android_test_luizalabs.presentation.detail.DetailPresenterImpl
import com.android_test_luizalabs.presentation.favorite.FavoritePresenterImpl
import com.android_test_luizalabs.presentation.list.ListPresenterImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: AppDatabase): GistDao {
        return dataBase.gistDao()
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

val appModule = module {
    single { Service().createService(GistService::class.java) }

    single<GistRemoteDataSource> { GistRemoteDataSourceImpl(gistService = get()) }
    single<GistLocalDataSource> { GistLocalDataSourceImpl(gistDao = get()) }
    single<GistDetailRemoteDataSource> { GistDetailRemoteDataSourceImpl(gistService = get()) }

    single<GistRepository> {
        GistRepositoryImpl(
            gistRemoteDataSource = get(),
            gistLocalDataSource = get()
        )
    }
    single<GistDetailRepository> { GistDetailRepositoryImpl(gistDetailRemoteDataSource = get()) }

    single<GetGistUseCase> { GetGistUseCaseImpl(repository = get()) }
    single<GetLocalGistUseCase> { GetLocalGistUseCaseImpl(repository = get()) }
    single<GetGistDetailUseCase> { GetGistDetailUseCaseImpl(repository = get()) }
    single<SetLocalGistUseCase> { SetLocalGistUseCaseImpl(repository = get()) }
    single<RemoveLocalGistUseCase> { RemoveLocalGistUseCaseImpl(repository = get()) }

    single { DetailPresenterImpl(getGistDetailUseCase = get()) }

    single {
        FavoritePresenterImpl(
            getLocalGistUseCase = get(),
            removeLocalGistUseCase = get(),
            setLocalGistUseCase = get()
        )
    }
}