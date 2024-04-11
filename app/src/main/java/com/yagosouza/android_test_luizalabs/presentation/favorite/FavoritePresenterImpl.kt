package com.yagosouza.android_test_luizalabs.presentation.favorite

import com.yagosouza.android_test_luizalabs.core.base.LifecycleScope
import com.yagosouza.android_test_luizalabs.domain.usecase.GetLocalGistUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoritePresenterImpl(private val getLocalGistUseCase: GetLocalGistUseCase) :
    FavoriteContract.Presenter, LifecycleScope() {

    private var view: FavoriteContract.View? = null

    override fun fetchGist() {
        launch {
            getLocalGistUseCase.invoke()
                .onStart { view?.displayLoading(true) }
                .onCompletion { view?.displayLoading(false) }
                .catch { view?.showError(it) }
                .collect { view?.displayGist(it) }
        }
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