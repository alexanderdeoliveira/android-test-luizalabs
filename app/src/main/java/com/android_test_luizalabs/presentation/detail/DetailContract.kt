package com.android_test_luizalabs.presentation.detail

import com.android_test_luizalabs.core.base.BaseContract
import com.android_test_luizalabs.domain.model.Gist

interface DetailContract : BaseContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun fetchDetail(id: String)
    }

    interface View : BaseContract.View {

        fun displayGist(gistDetail: Gist)

        fun displayLoading(isLoading: Boolean)

        fun showError(error: Throwable)
    }
}