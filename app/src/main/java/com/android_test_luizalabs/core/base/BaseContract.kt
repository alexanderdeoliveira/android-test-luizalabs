package com.android_test_luizalabs.core.base

interface BaseContract {

    interface View

    interface Presenter<T : View> {

        fun attachView(view: T)

        fun detachView()
    }
}