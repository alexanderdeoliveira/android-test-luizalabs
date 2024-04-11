package com.yagosouza.android_test_luizalabs

import android.app.Application
import com.yagosouza.android_test_luizalabs.di.appModule
import com.yagosouza.android_test_luizalabs.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(databaseModule, appModule)
        }
    }
}