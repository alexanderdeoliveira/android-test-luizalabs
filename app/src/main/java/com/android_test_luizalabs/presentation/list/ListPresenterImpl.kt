package com.android_test_luizalabs.presentation.list

import com.android_test_luizalabs.core.base.LifecycleScope
import com.android_test_luizalabs.domain.model.Gist
import com.android_test_luizalabs.domain.usecase.GetGistUseCase
import com.android_test_luizalabs.domain.usecase.SetLocalGistUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ListPresenterImpl(
    private val getGistUseCase: GetGistUseCase,
    private val setLocalGistUseCase: SetLocalGistUseCase
) : ListContract.Presenter, LifecycleScope() {

    private var view: ListContract.View? = null

    override fun fetchGist() {
        launch {
            getGistUseCase.invoke()
                .onStart { view?.displayLoading(true) }
                .onCompletion { view?.displayLoading(false) }
                .catch { view?.showError(it) }
                .collect { view?.displayGist(it) }
        }
    }

    override fun saveFavorite(gist: Gist) {
        launch {
            setLocalGistUseCase.invoke(gist)
        }
    }

    override fun attachView(view: ListContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}