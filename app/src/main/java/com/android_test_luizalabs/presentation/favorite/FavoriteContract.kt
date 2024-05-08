package com.android_test_luizalabs.presentation.favorite

import com.android_test_luizalabs.core.base.BaseContract
import com.android_test_luizalabs.domain.model.Gist

interface FavoriteContract: BaseContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun fetchGist()

        fun removeFavorite(id: String)

        fun addFavorite(gist: Gist)

    }

    interface View: BaseContract.View {

        fun displayGist(list: List<Gist>)

        fun displayLoading(isLoading: Boolean)

        fun showError(error: Throwable)
    }
}