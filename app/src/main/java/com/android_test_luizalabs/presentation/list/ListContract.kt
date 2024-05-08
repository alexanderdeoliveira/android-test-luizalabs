package com.android_test_luizalabs.presentation.list

import com.android_test_luizalabs.core.base.BaseContract
import com.android_test_luizalabs.domain.model.Gist

interface ListContract : BaseContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun fetchGist()

        fun saveFavorite(gist: Gist)
    }

    interface View : BaseContract.View {

        fun displayGist(list: List<Gist>)

        fun displayLoading(isLoading: Boolean)

        fun showError(error: Throwable)

        fun onItemSelected(id: String)

    }
}