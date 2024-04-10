import com.yagosouza.android_test_luizalabs.domain.model.Files
import com.yagosouza.android_test_luizalabs.domain.model.Gist
import com.yagosouza.android_test_luizalabs.domain.model.Owner
import com.yagosouza.android_test_luizalabs.domain.usecase.GetGistUseCase
import com.yagosouza.android_test_luizalabs.presentation.list.ListContract
import com.yagosouza.android_test_luizalabs.presentation.list.ListPresenterImpl
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class ListPresenterImplTest {

    @MockK
    private lateinit var mockView: ListContract.View

    @MockK
    private lateinit var mockGetGistUseCase: GetGistUseCase

    private lateinit var presenter: ListPresenterImpl

    @Before
    fun setUp() {
        presenter = ListPresenterImpl(mockGetGistUseCase)
        presenter.attachView(mockView)
    }

    @Test
    fun `fetchGist should display loading and gist on success`() {
        val mockGist = listOf(Gist("id", Files("files"), "description", Owner(null, null)))
        val page = 1

        coEvery { mockGetGistUseCase.invoke(page) } returns flowOf(mockGist)

        presenter.fetchGist(page)

        verify {
            mockView.displayLoading(true)
            mockView.displayLoading(false)
            mockView.displayGist(mockGist)
        }
        confirmVerified(mockView, mockGetGistUseCase)
    }

    @Test
    fun `fetchGist should display loading and error on failure`() {
        val error = Throwable("Error fetching gist")
        val page = 1

        coEvery { mockGetGistUseCase.invoke(page) } returns flowOf(emptyList())

        presenter.fetchGist(page)

        verify {
            mockView.displayLoading(true)
            mockView.displayLoading(false)
            mockView.showError(error)
        }
        confirmVerified(mockView, mockGetGistUseCase)
    }
}