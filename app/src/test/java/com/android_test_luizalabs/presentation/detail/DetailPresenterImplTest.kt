package com.android_test_luizalabs.presentation.detail

import com.android_test_luizalabs.CoroutineTestRule
import com.android_test_luizalabs.domain.usecase.GetGistDetailUseCase
import com.android_test_luizalabs.stubs.getSubGist
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailPresenterImplTest {

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    private val getGistDetailUseCase: GetGistDetailUseCase = mockk(relaxed = true)
    private val view: DetailContract.View = mockk(relaxed = true)

    private val detailPresenter = DetailPresenterImpl(getGistDetailUseCase)

    @Before
    fun setup() {
        detailPresenter.attachView(view)
    }

    @After
    fun done() {
        detailPresenter.detachView()
    }

    @Test
    fun `fetchGist Should emit Gist When success`() {
        runCatching {
            //Given
            val stubGist = getSubGist()
            val id = "c56c877e90117ee111f04b7e6e203e26"
            every { (getGistDetailUseCase.invoke(id)) } returns flow { emit(stubGist) }

            //When
            detailPresenter.fetchDetail(id)

            //Then
            verify {
                view.displayLoading(true)
                view.displayGist(stubGist)
                view.displayLoading(false)
            }
        }
    }

    @Test
    fun `fetchGist Should emit Throwable When error Response`() {
        runCatching {
            //Given
            val error = Throwable()
            val id = "c56c877e90117ee111f04b7e6e203e26"

            every { (getGistDetailUseCase.invoke(id)) } returns flow { throw error }

            //When
            detailPresenter.fetchDetail(id)

            //Then
            verify {
                view.displayLoading(true)
                view.showError(error)
                view.displayLoading(false)
            }

        }
    }
}