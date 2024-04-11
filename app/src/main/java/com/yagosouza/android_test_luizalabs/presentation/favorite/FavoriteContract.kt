package com.yagosouza.android_test_luizalabs.presentation.favorite

import com.yagosouza.android_test_luizalabs.base.BaseContract
import com.yagosouza.android_test_luizalabs.domain.model.Gist

interface FavoriteContract: BaseContract {

    interface Presenter : BaseContract.Presenter<View> {

        fun fetchGist()

        fun removeFavorite()

        fun addFavorite()

    }

    interface View: BaseContract.View {

        fun displayGist(list: List<Gist>)

        fun displayLoading(isLoading: Boolean)

        fun showError(error: Throwable)
    }
}