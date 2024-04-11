package com.yagosouza.android_test_luizalabs.di

import android.app.Application
import androidx.room.Room
import com.yagosouza.android_test_luizalabs.data.api.GistService
import com.yagosouza.android_test_luizalabs.data.datasource.GistDetailRemoteDataSource
import com.yagosouza.android_test_luizalabs.data.datasource.GistDetailRemoteDataSourceImpl
import com.yagosouza.android_test_luizalabs.data.datasource.GistLocalDataSource
import com.yagosouza.android_test_luizalabs.data.datasource.GistLocalDataSourceImpl
import com.yagosouza.android_test_luizalabs.data.datasource.GistRemoteDataSource
import com.yagosouza.android_test_luizalabs.data.datasource.GistRemoteDataSourceImpl
import com.yagosouza.android_test_luizalabs.data.local.AppDatabase
import com.yagosouza.android_test_luizalabs.data.local.GistDao
import com.yagosouza.android_test_luizalabs.data.repository.GistDetailRepositoryImpl
import com.yagosouza.android_test_luizalabs.data.repository.GistRepositoryImpl
import com.yagosouza.android_test_luizalabs.domain.repository.GistDetailRepository
import com.yagosouza.android_test_luizalabs.domain.repository.GistRepository
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistDetailUseCase
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistDetailUseCaseImpl
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistUseCase
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistUseCaseImpl
import com.yagosouza.android_test_luizalabs.domain.usecase.GetLocalGistUseCase
import com.yagosouza.android_test_luizalabs.domain.usecase.GetLocalGistUseCaseImpl
import com.yagosouza.android_test_luizalabs.domain.usecase.RemoveLocalGistUseCase
import com.yagosouza.android_test_luizalabs.domain.usecase.RemoveLocalGistUseCaseImpl
import com.yagosouza.android_test_luizalabs.domain.usecase.SetLocalGistUseCase
import com.yagosouza.android_test_luizalabs.domain.usecase.SetLocalGistUseCaseImpl
import com.yagosouza.android_test_luizalabs.helper.network.Service
import com.yagosouza.android_test_luizalabs.presentation.detail.DetailPresenterImpl
import com.yagosouza.android_test_luizalabs.presentation.favorite.FavoritePresenterImpl
import com.yagosouza.android_test_luizalabs.presentation.list.ListPresenterImpl
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

    factory<GistRemoteDataSource> { GistRemoteDataSourceImpl(gistService = get()) }
    factory<GistLocalDataSource> { GistLocalDataSourceImpl(gistDao = get()) }
    factory<GistDetailRemoteDataSource> { GistDetailRemoteDataSourceImpl(gistService = get()) }

    factory<GistRepository> {
        GistRepositoryImpl(
            gistRemoteDataSource = get(),
            gistLocalDataSource = get()
        )
    }
    factory<GistDetailRepository> { GistDetailRepositoryImpl(gistDetailRemoteDataSource = get()) }

    factory<GetGistUseCase> { GetGistUseCaseImpl(repository = get()) }
    factory<GetLocalGistUseCase> { GetLocalGistUseCaseImpl(repository = get()) }
    factory<GetGistDetailUseCase> { GetGistDetailUseCaseImpl(repository = get()) }
    factory<SetLocalGistUseCase> { SetLocalGistUseCaseImpl(repository = get()) }
    factory<RemoveLocalGistUseCase> { RemoveLocalGistUseCaseImpl(repository = get()) }

    factory { ListPresenterImpl(getGistUseCase = get(), setLocalGistUseCase = get()) }

    factory { DetailPresenterImpl(getGistDetailUseCase = get()) }

    factory {
        FavoritePresenterImpl(
            getLocalGistUseCase = get(),
            removeLocalGistUseCase = get(),
            setLocalGistUseCase = get()
        )
    }
}