import com.android_test_luizalabs.CoroutineTestRule
import com.android_test_luizalabs.domain.usecase.GetGistUseCase
import com.android_test_luizalabs.domain.usecase.SetLocalGistUseCase
import com.android_test_luizalabs.presentation.list.ListContract
import com.android_test_luizalabs.presentation.list.ListPresenterImpl
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
class ListPresenterImplTest {

    @get:Rule
    var mainCoroutineRule = CoroutineTestRule()

    private val getGistUseCase: GetGistUseCase = mockk(relaxed = true)
    private val setLocalGistUseCase: SetLocalGistUseCase = mockk(relaxed = true)
    private val view: ListContract.View = mockk(relaxed = true)

    private val listPresenter = ListPresenterImpl(getGistUseCase, setLocalGistUseCase)

    @Before
    fun setup() {
        listPresenter.attachView(view)
    }

    @After
    fun done() {
        listPresenter.detachView()
    }

    @Test
    fun `fetchGist Should emit Gist list When success`() {
        runCatching {
            //Given
            val stubGistList = getSubGistList()
            every { (getGistUseCase.invoke(0)) } returns flow { emit(stubGistList) }

            //When
            listPresenter.fetchGist()

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
            every { (getGistUseCase.invoke(0)) } returns flow { throw error }

            //When
            listPresenter.fetchGist()

            //Then
            verify {
                view.displayLoading(true)
                view.showError(error)
                view.displayLoading(false)
            }

        }
    }
}