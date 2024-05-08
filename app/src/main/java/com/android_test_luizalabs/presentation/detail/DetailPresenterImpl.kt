package com.android_test_luizalabs.presentation.detail

import com.android_test_luizalabs.core.base.LifecycleScope
import com.android_test_luizalabs.domain.usecase.GetGistDetailUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailPresenterImpl(private val getGistDetailUseCase: GetGistDetailUseCase) :
    DetailContract.Presenter, LifecycleScope() {

    private var view: DetailContract.View? = null

    override fun fetchDetail(id: String) {
        launch {
            getGistDetailUseCase.invoke(id)
                .onStart { view?.displayLoading(true) }
                .onCompletion { view?.displayLoading(false) }
                .catch { view?.showError(it) }
                .collect { view?.displayGist(it) }
        }
    }

    override fun attachView(view: DetailContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}