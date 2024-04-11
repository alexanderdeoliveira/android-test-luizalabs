package com.yagosouza.android_test_luizalabs.presentation.list

import com.yagosouza.android_test_luizalabs.core.base.LifecycleScope
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ListPresenterImpl(private val getGistUseCase: GetGistUseCase) : ListContract.Presenter,
    LifecycleScope() {

    private var view: ListContract.View? = null

    override fun fetchGist(page: Int) {
        launch {
            getGistUseCase.invoke(page)
                .onStart { view?.displayLoading(true) }
                .onCompletion { view?.displayLoading(false) }
                .catch { view?.showError(it) }
                .collect { view?.displayGist(it) }
        }
    }

    override fun saveFavorite(gist: Gist) {

    }

    override fun attachView(view: ListContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}