package com.yagosouza.android_test_luizalabs.presentation.favorite

import com.yagosouza.android_test_luizalabs.base.LifecycleScope

class FavoritePresenterImpl: FavoriteContract.Presenter, LifecycleScope() {

    private var view: FavoriteContract.View? = null

    override fun fetchGist() {
        //TODO("Not yet implemented")
    }

    override fun removeFavorite() {
        //TODO("Not yet implemented")
    }

    override fun addFavorite() {
        //TODO("Not yet implemented")
    }

    override fun attachView(view: FavoriteContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}