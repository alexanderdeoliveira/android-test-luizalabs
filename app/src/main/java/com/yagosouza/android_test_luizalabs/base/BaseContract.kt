package com.yagosouza.android_test_luizalabs.base

interface BaseContract {

    interface View

    interface Presenter<T : View> {

        fun attachView(view: T)

        fun detachView()
    }
}