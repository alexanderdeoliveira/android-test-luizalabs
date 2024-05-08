package com.android_test_luizalabs.presentation.favorite

import com.android_test_luizalabs.CoroutineTestRule
import com.android_test_luizalabs.domain.usecase.GetLocalGistUseCase
import com.android_test_luizalabs.domain.usecase.RemoveLocalGistUseCase
import com.android_test_luizalabs.domain.usecase.SetLocalGistUseCase
import com.android_test_luizalabs.stubs.getSubGistList
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
class FavoritePresenterImplTest {

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    private val getLocalGistUseCase: GetLocalGistUseCase = mockk(relaxed = true)
    private val setLocalGistUseCase: SetLocalGistUseCase = mockk(relaxed = true)
    private val removeLocalGistUseCase: RemoveLocalGistUseCase = mockk(relaxed = true)
    private val view: FavoriteContract.View = mockk(relaxed = true)

    private val favoritePresenter =
        FavoritePresenterImpl(getLocalGistUseCase, removeLocalGistUseCase, setLocalGistUseCase)

    @Before
    fun setup() {
        favoritePresenter.attachView(view)
    }

    @After
    fun done() {
        favoritePresenter.detachView()
    }

    @Test
    fun `fetchGist Should emit Local Gist list When success`() {
        runCatching {
            //Given
            val stubGistList = getSubGistList()
            every { (getLocalGistUseCase.invoke()) } returns flow { emit(stubGistList) }

            //When
            favoritePresenter.fetchGist()

            //Then
            verify {
                view.displayLoading(true)
                view.displayGist(stubGistList)
                view.displayLoading(false)
            }
        }
    }

    @Test
    fun `fetchGist Should emit Throwable When error Response`() {
        runCatching {
            //Given
            val error = Throwable()
            every { (getLocalGistUseCase.invoke()) } returns flow { throw error }

            //When
            favoritePresenter.fetchGist()

            //Then
            verify {
                view.displayLoading(true)
                view.showError(error)
                view.displayLoading(false)
            }

        }
    }
}