package com.android_test_luizalabs.presentation.favorite

import com.android_test_luizalabs.core.base.LifecycleScope
import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.domain.usecase.GetLocalGistUseCase
import com.android_test_luizalabs.domain.usecase.RemoveLocalGistUseCase
import com.android_test_luizalabs.domain.usecase.SetLocalGistUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoritePresenterImpl(
    private val getLocalGistUseCase: GetLocalGistUseCase,
    private val removeLocalGistUseCase: RemoveLocalGistUseCase,
    private val setLocalGistUseCase: SetLocalGistUseCase
) :
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

    override fun removeFavorite(id: String) {
        launch {
            removeLocalGistUseCase.invoke(id)
        }
    }

    override fun addFavorite(gist: Gist) {
        launch { setLocalGistUseCase.invoke(gist) }
    }

    override fun attachView(view: FavoriteContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}